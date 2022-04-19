package io.github.kaczmarek.alarms.schedule.data

import android.content.Context
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import io.github.kaczmarek.alarms.schedule.data.ScheduleWorker.Companion.DESCRIPTION_KEY
import io.github.kaczmarek.alarms.schedule.data.ScheduleWorker.Companion.TITLE_KEY
import java.util.concurrent.TimeUnit

class ScheduleServiceImpl(private val context: Context) : ScheduleService {

    private val workManager by lazy { WorkManager.getInstance(context) }

    override fun setSchedule(title: String, description: String, repeatPeriod: Long) {
        val data: Data = Data.Builder()
            .putString(TITLE_KEY, title)
            .putString(DESCRIPTION_KEY, description)
            .build()

        val request =
            PeriodicWorkRequestBuilder<ScheduleWorker>(repeatPeriod, TimeUnit.MINUTES)
                .addTag("ReminderWorkTag")
                .setInputData(data)
                .build()

        workManager.enqueue(request)
    }

    override fun deleteSchedule() {
        workManager.cancelAllWork()
    }
}