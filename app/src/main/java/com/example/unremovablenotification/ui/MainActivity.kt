package com.example.unremovablenotification.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unremovablenotification.R
import com.example.unremovablenotification.data.AppStorage
import com.example.unremovablenotification.util.NotificationUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayNotification()
        initViews()
    }

    private fun initViews() {
        btnLogout.setOnClickListener { onLogoutClick() }
        tvTitle.text = getString(R.string.format_notification_user, AppStorage.getAuthInfo())
    }

    private fun onLogoutClick() {
        AppStorage.logout()

        NotificationUtil.removeNotification(this)
        startLoginActivity()
    }

    private fun displayNotification() {
        val loginInfo = AppStorage.getAuthInfo() ?: run {
            startLoginActivity()
            return
        }
        NotificationUtil.showNotification(this, loginInfo)
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}