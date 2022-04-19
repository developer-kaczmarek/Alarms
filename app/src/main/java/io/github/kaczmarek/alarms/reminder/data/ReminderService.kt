package io.github.kaczmarek.alarms.reminder.data

interface ReminderService {

    fun setReminder(title: String, description: String, timeInMillis: Long)

    fun deleteReminder()
}