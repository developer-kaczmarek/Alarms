package io.github.kaczmarek.alarms.schedule.ui

import me.aartikov.sesame.compose.form.control.InputControl

interface ScheduleComponent {

    val titleInput: InputControl

    val descriptionInput: InputControl

    val setReminderButtonEnabled: Boolean

    fun onSetReminderClick()

    fun onDeleteRemindersClick()
}