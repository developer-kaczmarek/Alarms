package io.github.kaczmarek.alarms.reminder.data

interface ReminderService {

    fun setReminder(title: String, description: String, repeatPeriod: Long)

    fun deleteReminders()
}