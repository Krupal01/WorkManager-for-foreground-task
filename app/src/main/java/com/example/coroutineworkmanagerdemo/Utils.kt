package com.example.coroutineworkmanagerdemo

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build

class Utils {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private lateinit var pendingIntent: PendingIntent

    @SuppressLint("UnspecifiedImmutableFlag")
    fun notificationCreate(
        ctx: Context,
        smallIcon: Int,
        title: String,
        shortDescription: String,
        activityClass : Class<*>
    ): Notification.Builder {
        val notificationIntent = Intent(ctx, activityClass)
        notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        pendingIntent = PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationChannel = NotificationChannel(Tags.CHANNEL_ID,Tags.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(ctx, Tags.CHANNEL_ID)
                .setSmallIcon(smallIcon)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(shortDescription)
        } else {

            builder = Notification.Builder(ctx)
                .setSmallIcon(smallIcon)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(shortDescription)
        }
//        notificationManager.notify(notificationId, builder.build())
        return builder
    }

    companion object{

    }
}