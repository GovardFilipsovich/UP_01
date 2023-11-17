package com.example.firebase_task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_task3.databinding.ActivityProductsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Products : AppCompatActivity() {
    private lateinit var bind: ActivityProductsBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(bind.root)
        auth = Firebase.auth

        var source = arrayListOf<Product>(Product(1, "Test", 200, "https://cdn-img.perekrestok.ru/i/800x800-fit/xdelivery/files/cf/d8/e8070dc80a8766be36149efb069c.jpg"))
        source = arrayListOf<Product>()

        val db = Firebase.firestore
        var role: Long = 0

        // Add a new document with a generated ID
        db.collection("Products").get().addOnSuccessListener {
            it.forEach {
                source.add(it.toObject(Product::class.java))
                Log.i("tag", it.toString())
            }
            var adapter = ProductAdapter(source)
            bind.productsList.adapter = adapter
            bind.productsList.layoutManager = LinearLayoutManager(this@Products)
        }

        bind.buttonOrder.setOnClickListener {
            var max = 0L
            db.collection("Orders").get().addOnSuccessListener {
                it.forEach {
                    Log.e("tag", it.get("id").toString())
                    if (max < it.get("id") as Long){
                        max = it.get("id") as Long
                    }
                }

                Log.e("tag", max.toString())
                Log.i("tag", source.toString())
                var sum = 0
                var products = arrayListOf<Product>()
                source.forEach {
                    if (it.count != null){
                        products.add(it)
                        sum += it.count!! * it.Cost
                    }
                }
                var order = Order(max+1L, products, time = 1800, sum, auth.uid!!)
                Log.i("tag", order.toString())
                val db = Firebase.firestore
                db.collection("Orders")
                    .add(order)
                    .addOnSuccessListener { documentReference ->
                        Log.d("tag", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("tag", "Error adding document", e)
                    }
            }
        }
    }
}