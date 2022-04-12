package io.github.kaczmarek.alarms.reminder.data

import android.content.Context
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import io.github.kaczmarek.alarms.reminder.data.ReminderWorker.Companion.DESCRIPTION_KEY
import io.github.kaczmarek.alarms.reminder.data.ReminderWorker.Companion.TITLE_KEY
import java.util.concurrent.TimeUnit

class ReminderServiceImpl(private val context: Context) : ReminderService {

    private val workManager by lazy { WorkManager.getInstance(context) }

    override fun setReminder(title: String, description: String, repeatPeriod: Long) {
        val data: Data = Data.Builder()
            .putString(TITLE_KEY, title)
            .putString(DESCRIPTION_KEY, description)
            .build()

        val request =
            PeriodicWorkRequestBuilder<ReminderWorker>(repeatPeriod, TimeUnit.MINUTES)
                .addTag("ReminderWorkTag")
                .setInputData(data)
                .build()

        workManager.enqueue(request)
    }

    override fun deleteReminders() {
        workManager.cancelAllWork()
    }
}