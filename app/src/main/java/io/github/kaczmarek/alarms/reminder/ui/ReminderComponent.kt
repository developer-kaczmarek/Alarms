package io.github.kaczmarek.alarms.reminder.ui

import io.github.kaczmarek.alarms.core.widget.dialog.DatePickerDialogData
import io.github.kaczmarek.alarms.core.widget.dialog.DialogResult
import io.github.kaczmarek.alarms.core.widget.dialog.TimePickerDialogData
import me.aartikov.sesame.compose.form.control.InputControl
import me.aartikov.sesame.dialog.DialogControl

interface ReminderComponent {

    val titleInput: InputControl

    val descriptionInput: InputControl

    val setReminderButtonEnabled: Boolean

    val timePickerDialogControl: DialogControl<TimePickerDialogData, DialogResult>

    val datePickerDialogControl: DialogControl<DatePickerDialogData, DialogResult>

    fun onSetReminderClick()

    fun onDeleteReminderClick()

    fun onShowTimePickerDialogClick()

    fun onShowDatePickerDialogClick()
}