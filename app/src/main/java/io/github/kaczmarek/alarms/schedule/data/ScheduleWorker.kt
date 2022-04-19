package io.github.kaczmarek.alarms.schedule.data

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.github.kaczmarek.alarms.core.utils.showNotification

class ScheduleWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    companion object {
        const val TITLE_KEY = "title_key"
        const val DESCRIPTION_KEY = "description_key"
    }

    override fun doWork(): Result {
        try {
            showNotification(
                context = context,
                title = inputData.getString(TITLE_KEY) ?: "",
                description = inputData.getString(DESCRIPTION_KEY) ?: ""
            )
        } catch (ex: Exception) {
            return Result.failure()
        }
        return Result.success()
    }
}