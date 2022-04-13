package io.github.kaczmarek.alarms.core.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import io.github.kaczmarek.alarms.R

fun showNotification(
    context: Context,
    title: String,
    description: String
) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val notificationBuilder =
        NotificationCompat.Builder(context, getChannelId(context, notificationManager))
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText(description)
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
            .setPriority(NotificationCompat.PRIORITY_MAX)

    notificationManager.notify(303, notificationBuilder.build())
}

private fun getChannelId(context: Context, notificationManager: NotificationManager): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val channelId = context.getString(R.string.alarms_channel_id)
        NotificationChannel(
            channelId,
            context.getString(R.string.alarms_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            lockscreenVisibility = android.app.Notification.VISIBILITY_PUBLIC
            description = context.getString(R.string.alarms_channel_description)
            notificationManager.createNotificationChannel(this)
        }
        return channelId
    } else ""
}