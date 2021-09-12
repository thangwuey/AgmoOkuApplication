package com.example.agmookuapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignUp: Button
    private lateinit var registerRegisterBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.registerEmail)
        editTextPassword = findViewById(R.id.registerPassword)
        buttonSignUp = findViewById(R.id.register)
        registerRegisterBtn = findViewById(R.id.registerLoginButton)

        buttonSignUp.setOnClickListener {

            var email: String = editTextEmail.text.toString()
            var password: String = editTextPassword.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show()
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener {task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                    else {
                        Toast.makeText(this, "Registered Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        registerRegisterBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}