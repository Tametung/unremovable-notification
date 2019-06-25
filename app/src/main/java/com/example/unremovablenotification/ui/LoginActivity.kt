package com.example.unremovablenotification.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unremovablenotification.R
import com.example.unremovablenotification.data.AppStorage
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (AppStorage.isAuthContain()) {
            startMainScreen()
            return
        }

        initViews()
    }

    private fun initViews() {
        btnLogin.setOnClickListener { onLoginClick() }
    }

    private fun onLoginClick() {
        val login = etLogin.text.toString()
        val password = etPassword.text.trim().toString()

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_fill_field), Toast.LENGTH_LONG).show()
            return
        }

        AppStorage.setAuthInfo(login)
        startMainScreen()
    }

    private fun startMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}