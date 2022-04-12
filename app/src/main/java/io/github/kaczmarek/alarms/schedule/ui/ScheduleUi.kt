package io.github.kaczmarek.alarms.schedule.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.theme.AppTheme
import io.github.kaczmarek.alarms.core.widget.CommonTextField
import me.aartikov.sesame.compose.form.control.InputControl

@Composable
fun ScheduleUi(
    component: ScheduleComponent,
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
                stringResource(id = R.string.reminder_title_hint)
            )

            CommonTextField(
                component.descriptionInput,
                stringResource(id = R.string.reminder_description_hint)
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ScheduleButton(
                    text = stringResource(R.string.reminder_set_reminder),
                    onClick = component::onSetReminderClick,
                    enabled = component.setReminderButtonEnabled
                )

                ScheduleButton(
                    text = stringResource(R.string.reminder_delete_reminders),
                    onClick = component::onDeleteRemindersClick,
                    enabled = true
                )
            }
        }
    }
}

@Composable
fun RowScope.ScheduleButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.weight(1f),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text.uppercase(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScheduleUiPreview() {
    AppTheme {
        ScheduleUi(FakeScheduleComponent())
    }
}

class FakeScheduleComponent : ScheduleComponent {

    override val titleInput = InputControl(
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )

    override val descriptionInput = InputControl(
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )
    override val setReminderButtonEnabled = false

    override fun onDeleteRemindersClick() = Unit

    override fun onSetReminderClick() = Unit
}