package com.example.unremovablenotification.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.unremovablenotification.R
import com.example.unremovablenotification.ui.MainActivity

object NotificationUtil {
    private const val ID_NOTIFICATION = 12345678
    private const val CHANNEL_ID = "com.example.unremovablenotification.channel_id"
    private const val CHANNEL_NAME = "NOTIFICATION_PROJECT_CHANNEL"

    fun showNotification(context: Context, loginInfo: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val resultIntent = Intent(context, MainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(context.getString(R.string.format_notification_user, loginInfo))
            .setOngoing(true)
            .setAutoCancel(false)
            .setContentIntent(resultPendingIntent)

        with(NotificationManagerCompat.from(context)) {
            val notification = builder.build()
            notification.flags = notification.flags or Notification.FLAG_NO_CLEAR
            notify(CHANNEL_ID, ID_NOTIFICATION, notification)
        }
    }

    fun removeNotification(context: Context) {
        NotificationManagerCompat.from(context).cancel(CHANNEL_ID, ID_NOTIFICATION)
    }
}