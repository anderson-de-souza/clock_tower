package com.anderson.clocktower

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class ClockTowerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel(this)
    }

    companion object {

        val NOTIFICATION_CHANNEL: String = "com.anderson.channel.Main"

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    NOTIFICATION_CHANNEL, context.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }
        }

    }

}