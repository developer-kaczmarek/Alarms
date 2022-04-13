package io.github.kaczmarek.alarms.core.widget.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import me.aartikov.sesame.dialog.DialogControl

@Composable
fun ShowTimePickerDialog(dialogControl: DialogControl<TimePickerDialogData, DialogResult>) {
    ShowDialog(dialogControl) { data ->
        TimePickerDialogUi(data, dialogControl)
    }
}

@Composable
fun ShowDatePickerDialog(dialogControl: DialogControl<DatePickerDialogData, DialogResult>) {
    ShowDialog(dialogControl) { data ->
        DatePickerDialogUi(data, dialogControl)
    }
}

@Composable
fun TimePickerDialogUi(
    data: TimePickerDialogData,
    dialogControl: DialogControl<TimePickerDialogData, DialogResult>
) {
    TimePickerDialog(
        LocalContext.current,
        { _, hours: Int, minute: Int ->
            dialogControl.sendResult(DialogResult.Confirm(DateTime(hours = hours, minute = minute)))
        },
        data.hourOfDay,
        data.minute,
        true
    ).show()
}

@Composable
fun DatePickerDialogUi(
    data: DatePickerDialogData,
    dialogControl: DialogControl<DatePickerDialogData, DialogResult>
) {
    DatePickerDialog(
        LocalContext.current,
        { _, year: Int, month: Int, _ ->

            dialogControl.sendResult(DialogResult.Confirm(DateTime(year = year, month = month)))
        },
        data.year,
        data.month,
        data.dayOfMonth
    ).show()
}

data class DatePickerDialogData(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int
)

data class TimePickerDialogData(
    val hourOfDay: Int,
    val minute: Int
)

data class DateTime(
    val year: Int = 0,
    val month: Int = 0,
    val hours: Int = 0,
    val minute: Int = 0
)