package com.example.techtestalfa2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class LoginActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Install pertama tidak menunjukkan splash screen
        installSplashScreen()
        setContentView(R.layout.activity_login)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val studentHeader = findViewById<TextView>(R.id.appName)
        val usernameInput = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.usernameInput)
        val passwordInput = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = ProgressBar.INVISIBLE

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressBar.visibility = ProgressBar.VISIBLE
            studentHeader.visibility = View.GONE
            usernameInput.visibility = View.GONE
            passwordInput.visibility = View.GONE
            loginButton.visibility = View.GONE

            if (username == "alfagift-admin" && password == "asdf") {
                progressBar.postDelayed({
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1500)
            } else {
                progressBar.visibility = ProgressBar.INVISIBLE
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}