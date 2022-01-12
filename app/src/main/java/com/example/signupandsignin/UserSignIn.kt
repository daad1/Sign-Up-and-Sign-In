package com.example.signupandsignin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserSignIn : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var copassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var loginText: TextView


    private val database by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        name = findViewById(R.id.etName)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPass)
        copassword = findViewById(R.id.etConPass)
        btnSignIn = findViewById(R.id.btn_SignIn)
        loginText = findViewById(R.id.tvHaveAccount)

        val logIn = loginText.text.toString()
        val ss = SpannableString(logIn)
        val clickableSpan = object : ClickableSpan() {


            override fun onClick(p0: View) {
                startActivity(Intent(this@UserSignIn, MainActivity::class.java))
            }

        }
        ss.setSpan(clickableSpan, 17, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        loginText.text = ss
        loginText.movementMethod = LinkMovementMethod.getInstance()

        name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(t: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnSignIn.isEnabled =
                    t.toString().trim().isNotEmpty() && email.text.toString().trim()
                        .isNotEmpty() && password.text.toString().trim()
                        .isNotEmpty() && copassword.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(t: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnSignIn.isEnabled =
                    t.toString().trim().isNotEmpty() && name.text.toString().trim()
                        .isNotEmpty() && password.text.toString().trim()
                        .isNotEmpty() && copassword.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(t: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnSignIn.isEnabled =
                    t.toString().trim().isNotEmpty() && email.text.toString().trim()
                        .isNotEmpty() && name.text.toString().trim()
                        .isNotEmpty() && copassword.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        copassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(t: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnSignIn.isEnabled =
                    t.toString().trim().isNotEmpty() && email.text.toString().trim()
                        .isNotEmpty() && password.text.toString().trim()
                        .isNotEmpty() && name.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        btnSignIn.setOnClickListener {
            if (password.text.toString() == copassword.text.toString()) {
                val check = database.logIn(email.text.toString(), password.text.toString())
            } else {
                Toast.makeText(
                    this,
                    "Password and Confirm Password must be match.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}
