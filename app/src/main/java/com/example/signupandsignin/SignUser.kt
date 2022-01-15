package com.example.signupandsignin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUser : AppCompatActivity() {
    val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    private lateinit var etEmail: EditText
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConPassword: EditText
    private lateinit var signUp: Button
    private lateinit var user: Users


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        // initializing the views
        initViews()
        signUp.setOnClickListener {
            addUser()


        }
    }
    private fun addUser(){
        if (!etEmail.text.isNullOrEmpty() || !etUserName.text.isNullOrEmpty() || !etPassword.text.isNullOrEmpty() || !etConPassword.text.isNullOrEmpty()) {
            val email = etEmail.text.toString()
            val name = etUserName.text.toString()

            val mobile = etPassword.text.toString()

            val password = etConPassword.text.toString()
            val newUser = Users(email, name, mobile, password)
            Toast.makeText(this, "save Successfully", Toast.LENGTH_SHORT).show()
            if(databaseHelper.readData(email) == null){
                databaseHelper.saveData(newUser)
                val intent = Intent(this,UserDetails::class.java)
                intent.putExtra("newUser",newUser)
                startActivity(intent)
            }

        } else {
            Toast.makeText(this, "Not save Successfully", Toast.LENGTH_SHORT).show()
        }
    }



    fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etUserName = findViewById(R.id.etName)

        etPassword = findViewById(R.id.etPass)

        etConPassword = findViewById(R.id.etConPass)
        signUp = findViewById(R.id.btn_SignIn)

    }

}
