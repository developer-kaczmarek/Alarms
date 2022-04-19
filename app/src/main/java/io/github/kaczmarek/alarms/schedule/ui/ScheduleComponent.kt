package io.github.kaczmarek.alarms.schedule.ui

import me.aartikov.sesame.compose.form.control.InputControl

interface ScheduleComponent {

    val titleInput: InputControl

    val descriptionInput: InputControl

    val setScheduleButtonEnabled: Boolean

    fun onSetScheduleClick()

    fun onDeleteSchedulesClick()
}