package io.github.kaczmarek.alarms.reminder.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.github.kaczmarek.alarms.R

class ReminderWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    companion object {
        const val TITLE_KEY = "title_key"
        const val DESCRIPTION_KEY = "description_key"
    }

    override fun doWork(): Result {
        try {
            showNotification()
        } catch (ex: Exception) {
            return Result.failure()
        }
        return Result.success()
    }

    private fun showNotification() = with(context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder =
            NotificationCompat.Builder(this, getChannelId(notificationManager))
                .setContentTitle(inputData.getString(TITLE_KEY))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(inputData.getString(DESCRIPTION_KEY))
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_MAX)

        notificationManager.notify(303, notificationBuilder.build())
    }

    private fun getChannelId(notificationManager: NotificationManager): String = with(context) {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = getString(R.string.alarms_channel_id)
            NotificationChannel(
                channelId,
                getString(R.string.alarms_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                description = getString(R.string.alarms_channel_description)
                notificationManager.createNotificationChannel(this)
            }
            return channelId
        } else ""
    }
}