package io.github.kaczmarek.alarms.reminder.domain

import io.github.kaczmarek.alarms.reminder.data.ReminderService

class SetReminderInteractor(private val reminderService: ReminderService) {

    fun execute(title: String, description: String, timeInMillis: Long) {
        reminderService.setReminder(title, description, timeInMillis)
    }
}