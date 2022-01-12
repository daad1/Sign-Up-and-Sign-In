package com.example.signupandsignin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_log)

        val intent = intent.getStringExtra("email")
        val sqLiteDatabase = DatabaseHelper(applicationContext)
        val name = findViewById<TextView>(R.id.tvName)
        val email = findViewById<TextView>(R.id.tvEmail)
        val data = sqLiteDatabase.getDetails(intent.toString())

        if (data.isNotEmpty()) {
            name.text = "Name : ${data[0]}"
            email.text = "Email : ${data[1]}"
        }
    }
}