package com.planetapps.coin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignIn.KEY2)
        val mail = intent.getStringExtra(SignIn.KEY1)
        val userId = intent.getStringExtra(SignIn.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tVWelcome)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val idText = findViewById<TextView>(R.id.tvUnique)

        welcomeText.text = "WELCOME  $name"
        mailText.text = "e-mail : $mail"
        idText.text = "Unique ID : $userId"
    }
}