package com.example.unremovablenotification

import android.app.Application

class NotificationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

//        TestSdk.init(this)
    }

    companion object {

        lateinit var instance: NotificationApplication
            private set

        fun getInstance(): Application = instance
    }
}