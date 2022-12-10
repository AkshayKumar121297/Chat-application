package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnSignup : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.username)
        edtPassword = findViewById(R.id.userpassword)
        btnLogin = findViewById(R.id.btnlogin)
        btnSignup = findViewById(R.id.btnsignup)

        btnSignup.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password)
        }
    }

    private fun login(email: String, password : String){

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task->
            if(task.isSuccessful){
                val intent = Intent(this@Login, HomeActivity::class.java)
                finish()
                startActivity(intent)
            } else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

}