package io.github.kaczmarek.alarms.reminder.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.theme.AppTheme
import io.github.kaczmarek.alarms.core.widget.CommonButton
import io.github.kaczmarek.alarms.core.widget.CommonTextField
import io.github.kaczmarek.alarms.core.widget.dialog.*
import me.aartikov.sesame.compose.form.control.InputControl
import me.aartikov.sesame.dialog.DialogControl

@Composable
fun ReminderUi(
    component: ReminderComponent,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 8.dp)
        ) {
            CommonTextField(
                component.titleInput,
                stringResource(id = R.string.schedule_title_hint)
            )

            CommonTextField(
                component.descriptionInput,
                stringResource(id = R.string.common_description_hint)
            )

            Text(text = stringResource(id = R.string.reminder_settings_time))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CommonButton(
                    text = stringResource(R.string.reminder_selected_date),
                    onClick = component::onShowDatePickerDialogClick
                )

                CommonButton(
                    text = stringResource(R.string.reminder_selected_time),
                    onClick = component::onShowTimePickerDialogClick
                )
            }

            Text(text = stringResource(id = R.string.reminder_reminder_name))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CommonButton(
                    text = stringResource(R.string.reminder_set_reminder),
                    onClick = component::onSetReminderClick,
                    enabled = component.setReminderButtonEnabled
                )

                CommonButton(
                    text = stringResource(R.string.reminder_delete_reminder),
                    onClick = component::onDeleteReminderClick
                )
            }
        }
        ShowTimePickerDialog(dialogControl = component.timePickerDialogControl)
        ShowDatePickerDialog(dialogControl = component.datePickerDialogControl)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ReminderUiPreview() {
    AppTheme {
        ReminderUi(FakeReminderComponent())
    }
}

class FakeReminderComponent : ReminderComponent {

    override val titleInput = InputControl(
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )

    override val descriptionInput = InputControl(
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )
    override val setReminderButtonEnabled = false

    override val timePickerDialogControl = DialogControl<TimePickerDialogData, DialogResult>()

    override val datePickerDialogControl = DialogControl<DatePickerDialogData, DialogResult>()

    override fun onSetReminderClick() = Unit

    override fun onDeleteReminderClick() = Unit

    override fun onShowTimePickerDialogClick() = Unit

    override fun onShowDatePickerDialogClick() = Unit
}
