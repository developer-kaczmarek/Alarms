package io.github.kaczmarek.alarms.reminder.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent

class ReminderServiceImpl(private val context: Context) : ReminderService {

    private val alarmManager by lazy { context.getSystemService(ALARM_SERVICE) as AlarmManager }

    override fun setReminder(title: String, description: String, timeInMillis: Long) {
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra(ReminderReceiver.TITLE_KEY, title)
            putExtra(ReminderReceiver.DESCRIPTION_KEY, description)
        }
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        )
    }

    override fun deleteReminder() {
        val intent = Intent(context, ReminderReceiver::class.java)
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        )
    }
}