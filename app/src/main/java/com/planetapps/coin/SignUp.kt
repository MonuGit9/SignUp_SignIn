package com.planetapps.coin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val userId = findViewById<TextInputEditText>(R.id.etUserName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)


        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()

            val user = User(name, mail, password, uniqueId)

            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(uniqueId).setValue(user).addOnSuccessListener {

                etName.text?.clear();etMail.text?.clear();userId.text?.clear();userPassword.text?.clear()

                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show()}
        }

        val signIntext = findViewById<TextView>(R.id.tvSignIN)
        signIntext.setOnClickListener {
            val openSignInActivity = Intent(this, SignIn::class.java)
            startActivity(openSignInActivity)
        }
    }
}