package com.planetapps.coin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var databaseReference : DatabaseReference
    companion object{
        const val KEY1 = "com.planetapps.coin.SignIn.mail"
        const val KEY2 = "com.planetapps.coin.SignIn.name"
        const val KEY3 = "com.planetapps.coin.SignIn.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInbutton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.userNameEditText)

        signInbutton.setOnClickListener {
            val uniqueId = userName.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            } else{
                Toast.makeText(this, "Please enter Unique Id", Toast.LENGTH_SHORT ).show()
            }
        }
    }


    private fun readData(uniqueId: String) {
databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if(it.exists()){
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

                val intentWelcome = Intent(this, Welcome::class.java)
                intentWelcome.putExtra(KEY1, email.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3, userId.toString())
                startActivity(intentWelcome)

            }else{
                Toast.makeText(this, "User does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
       Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show()
        }
    }
}