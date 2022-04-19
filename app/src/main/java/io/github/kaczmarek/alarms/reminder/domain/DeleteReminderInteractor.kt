package io.github.kaczmarek.alarms.reminder.domain

import io.github.kaczmarek.alarms.reminder.data.ReminderService

class DeleteReminderInteractor(private val reminderService: ReminderService) {

    fun execute() {
        reminderService.deleteReminder()
    }
}