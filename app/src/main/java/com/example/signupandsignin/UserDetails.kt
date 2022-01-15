package com.example.signupandsignin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserDetails : AppCompatActivity() {
    val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var btnlogout: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_log)

        tvName = findViewById(R.id.user_Name)
        tvEmail = findViewById(R.id.user_Email)

        btnlogout = findViewById(R.id.btn_SignInUser)

        val dis: Users = intent.getSerializableExtra("newUser") as Users


        tvEmail.setText("Email:"+ dis.email)
        tvName.setText("Name:"+dis.name)

        Log.d("TAG", "onCreate:${dis} ")

        btnlogout.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }



    }
}