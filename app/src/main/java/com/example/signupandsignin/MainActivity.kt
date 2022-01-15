package com.example.signupandsignin
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var signUp:Button
    private lateinit var signin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signin = findViewById(R.id.btn_LogInMian)
        signUp = findViewById(R.id.btn_SingUp)
        signUp.setOnClickListener {
            val intent = Intent(this, SignUser::class.java)
            startActivity(intent)
        }
        signin.setOnClickListener {
            val intent = Intent(this, LoginUsers::class.java)
            startActivity(intent)
        }
    }
}