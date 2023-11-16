package com.example.firebase_task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import com.example.firebase_task3.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Registration : AppCompatActivity() {
    private lateinit var bind: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth
    var TAG = "tag"
    var role = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.rad.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if (b){
                role = 0
                bind.add.visibility = View.VISIBLE
                bind.textAdd.visibility = View.VISIBLE
                bind.textPhone.visibility = View.VISIBLE
                bind.phone.visibility = View.VISIBLE
            }else{
                bind.add.visibility = View.GONE
                bind.textAdd.visibility = View.GONE
                bind.textPhone.visibility = View.GONE
                bind.phone.visibility = View.GONE
                role = 1
            }
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        val db = Firebase.firestore


        var but = bind.but
        but.setOnClickListener {
            run{
                auth.createUserWithEmailAndPassword(bind.email.text.toString(), bind.pass.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                                val user_with_role = hashMapOf(
                                    "user" to user?.uid,
                                    "role" to role)
                            if (role == 0){
                                user_with_role.put("addres", bind.add.text.toString())
                                user_with_role.put("phone", bind.phone.text.toString())
                            }

                            db.collection("Users_with_roles")
                                .add(user_with_role)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }

                            var int = Intent(this@Registration, Profile::class.java)
                            startActivity(int)


                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }
    }
}