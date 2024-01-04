package com.example.purworejoapik

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    
    private var username = "admin"
    private var password = "admin"
    
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        val user = findViewById<EditText>(R.id.username)
        val pass = findViewById<EditText>(R.id.password)
        val btnlogin = findViewById<Button>(R.id.btn_login)
        
        btnlogin.setOnClickListener{
            if (user.text.isNotEmpty() && pass.text.isNotEmpty()){
                if (user.text.toString() == username && pass.text.toString() == password) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Username dan Password salah", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}