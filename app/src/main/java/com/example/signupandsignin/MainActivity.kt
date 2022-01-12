package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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

class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnLogIn: Button
    private lateinit var signInText: TextView

    private val database by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPass)
        btnLogIn = findViewById(R.id.btn_LogIn)
        signInText = findViewById(R.id.tvNewUser)


        val text = signInText.text.toString()
        val ss = SpannableString(text)
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(this@MainActivity, UserSignIn::class.java))
            }
        }
        ss.setSpan(clickableSpan1, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        signInText.text = ss
        signInText.movementMethod = LinkMovementMethod.getInstance()

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(t: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnLogIn.isEnabled =
                    t.toString().trim().isNotEmpty() && password.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(t: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnLogIn.isEnabled =
                    t.toString().trim().isNotEmpty() && email.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        btnLogIn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            val res = database.logIn(email, password)

            if (res.equals("true")) {
                val intent = Intent(this, UserLog::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }


        }

    }
}
