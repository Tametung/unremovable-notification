package com.example.unremovablenotification.data

import android.content.Context
import androidx.core.content.edit
import com.example.unremovablenotification.NotificationApplication

object AppStorage {
    private const val APP_STORAGE = "com.example.unremovablenotification.app_prefs"
    private const val LOGIN_KEY = "login_key"
    private val appPrefs by lazy { providePrefs() }

    fun setAuthInfo(login: String) = appPrefs.edit { putString(LOGIN_KEY, login) }

    fun getAuthInfo(): String? = appPrefs.getString(LOGIN_KEY, null)

    fun logout() = appPrefs.edit { clear() }

    fun isAuthContain(): Boolean = appPrefs.contains(LOGIN_KEY)

    private fun providePrefs() =
        NotificationApplication.getInstance().getSharedPreferences(APP_STORAGE, Context.MODE_PRIVATE)
}