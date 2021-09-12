package com.example.agmookuapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var editLoginTextEmail: EditText
    private lateinit var editLoginTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var loginRegisterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        editLoginTextEmail = findViewById(R.id.loginEmail)
        editLoginTextPassword = findViewById(R.id.loginPassword)
        buttonLogin = findViewById(R.id.login)
        loginRegisterBtn = findViewById(R.id.loginRegisterButton)

        buttonLogin.setOnClickListener {
            doLogin()
        }

        loginRegisterBtn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    private fun doLogin() {

        var email: String = editLoginTextEmail.text.toString()
        var password: String = editLoginTextPassword.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show()
        }else{

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = firebaseAuth.currentUser
                        startActivity(Intent(this,JobListActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}