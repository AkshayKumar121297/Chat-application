package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {

    private lateinit var edtName : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnSubmit : Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.name)
        edtEmail = findViewById(R.id.username1)
        edtPassword = findViewById(R.id.userpassword1)
        btnSubmit = findViewById(R.id.btnsubmit)

        btnSubmit.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
//            Log.d("result", email + " " + password)
            signup(name,email,password)
        }
    }
    private fun signup(name: String, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task->
            if(task.isSuccessful){
                addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                val intent = Intent(this@Signup, Login::class.java)
                finish()
                startActivity(intent)

            } else{
//                Log.d("akshay", task.exception.toString())
                Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addUserToDatabase(name: String,email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }



}