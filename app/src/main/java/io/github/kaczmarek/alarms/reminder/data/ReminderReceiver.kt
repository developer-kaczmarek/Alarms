package io.github.kaczmarek.alarms.reminder.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.kaczmarek.alarms.core.utils.showNotification

class ReminderReceiver : BroadcastReceiver() {

    companion object {
        const val TITLE_KEY = "title_key"
        const val DESCRIPTION_KEY = "description_key"
    }

    override fun onReceive(context: Context?, intent: Intent) {
        context?.let {
            showNotification(
                context = it,
                title = intent.extras?.getString(TITLE_KEY) ?: "",
                description = intent.extras?.getString(DESCRIPTION_KEY) ?: ""
            )
        }
    }
}