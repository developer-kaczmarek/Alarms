package io.github.kaczmarek.alarms.reminder.ui

import me.aartikov.sesame.compose.form.control.InputControl

interface ReminderComponent {

    val titleInput: InputControl

    val descriptionInput: InputControl

    val buttonsEnabled: Boolean

    fun onWorkManagerClick()

    fun onAlarmManagerClick()
}