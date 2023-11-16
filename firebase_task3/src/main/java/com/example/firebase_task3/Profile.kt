package com.example.firebase_task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebase_task3.databinding.ActivityProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {
    var TAG = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bind = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val user = Firebase.auth.currentUser
        Log.i("tag", user.toString())
        var name: String = ""
        var mail: String = ""
        var photoURL: String = ""
        var ver: String = ""
        var uid: String = ""
        user?.let {
            name = it.displayName.toString()
            Log.i("tag", it.displayName.toString())
            mail = it.email.toString()
            Log.i("tag", mail)
            photoURL = it.photoUrl.toString()
            Log.i("tag", photoURL)
            ver = it.isEmailVerified.toString()
            Log.i("tag", ver)
            uid = it.uid.toString()
            Log.i("tag", uid)
        }

        bind.name.text = "Name: $name"
        bind.mail.text = "Email: $mail"
        bind.photo.text = "PhotoURL: photoURL"
        bind.ver.text = "Is verified: $ver"
        bind.uid.text = "UID: $uid"

        val db = Firebase.firestore
        var role: Long = 0

        // Add a new document with a generated ID
        db.collection("Users_with_roles").get().addOnSuccessListener {
            Log.i("tag", it.toString())
            it.forEach{
                Log.i("tag", it.toString())
                if(it.get("user") == uid){
                    role = it.get("role") as Long
                }

            }
            var int: Intent
            if(role == 0L){
                Log.i("tag", "Client")
                int = Intent(this@Profile, Products::class.java)
            }else{
                Log.i("tag", "Curier")
                int = Intent(this@Profile, Orders::class.java)
            }
            startActivity(int)

        }


    }
}