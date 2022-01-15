package com.example.signupandsignin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginUsers : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPass)
        btnLogin = findViewById(R.id.btn_LogIn)


        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if (email !="" && password != "")
            {
                val login = databaseHelper.readData(email)
                Log.d("login", "onCreate: e_user")
                if(login != null)
                {
                    //check password
                    if (login.password == password)
                    {
                        val intent = Intent(this,UserDetails::class.java)
                        intent.putExtra("newUser",login)
                        startActivity(intent)
                        Log.d("intent", "onCreate: $intent")
                    }
                    else{
                        Toast.makeText(this,"wrong password", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(this,"User doesn't exists", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"please Enter all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}