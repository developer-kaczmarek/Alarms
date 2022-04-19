package io.github.kaczmarek.alarms.reminder.ui

import me.aartikov.sesame.compose.form.control.InputControl

interface ReminderComponent {

    val titleInput: InputControl

    val descriptionInput: InputControl

    val setReminderButtonEnabled: Boolean

    fun onSetReminderClick()

    fun onDeleteRemindersClick()
}