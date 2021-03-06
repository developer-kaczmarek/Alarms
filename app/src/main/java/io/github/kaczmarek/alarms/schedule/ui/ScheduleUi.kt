package io.github.kaczmarek.alarms.schedule.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.theme.AppTheme
import io.github.kaczmarek.alarms.core.widget.CommonButton
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
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 8.dp)
        ) {
            CommonTextField(
                modifier = Modifier.focusRequester(focusRequester),
                inputControl = component.titleInput,
                label = stringResource(id = R.string.reminder_title_hint)
            )

            CommonTextField(
                modifier = Modifier.focusRequester(focusRequester),
                inputControl = component.descriptionInput,
                label = stringResource(id = R.string.common_description_hint)
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CommonButton(
                    text = stringResource(R.string.schedule_set_schedule),
                    onClick = {
                        focusManager.clearFocus()
                        component.onSetScheduleClick()
                    },
                    enabled = component.setScheduleButtonEnabled
                )

                CommonButton(
                    text = stringResource(R.string.schedule_delete_schedule),
                    onClick = {
                        focusManager.clearFocus()
                        component.onDeleteSchedulesClick()
                    }
                )
            }
        }
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
    override val setScheduleButtonEnabled = false

    override fun onDeleteSchedulesClick() = Unit

    override fun onSetScheduleClick() = Unit
}