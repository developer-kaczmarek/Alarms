package io.github.kaczmarek.alarms.reminder.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaczmarek.alarms.core.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest
import me.aartikov.sesame.compose.form.control.InputControl
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.utils.resolve

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
            ReminderTextField(
                component.titleInput,
                stringResource(id = R.string.reminder_title_hint)
            )

            ReminderTextField(
                component.descriptionInput,
                stringResource(id = R.string.reminder_description_hint)
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.reminder_set_reminder),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ReminderButton(
                    text = stringResource(R.string.reminder_work_manager),
                    onClick = component::onWorkManagerClick,
                    enabled = component.buttonsEnabled
                )

                ReminderButton(
                    text = stringResource(R.string.reminder_alarm_manager),
                    onClick = component::onAlarmManagerClick,
                    enabled = component.buttonsEnabled
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReminderTextField(
    inputControl: InputControl,
    label: String,
    modifier: Modifier = Modifier
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
    ) {
        val focusRequester = remember { FocusRequester() }

        if (inputControl.hasFocus) {
            SideEffect {
                focusRequester.requestFocus()
            }
        }

        LaunchedEffect(key1 = inputControl) {
            inputControl.scrollToItEvent.collectLatest {
                bringIntoViewRequester.bringIntoView()
            }
        }

        OutlinedTextField(
            value = inputControl.text,
            keyboardOptions = inputControl.keyboardOptions,
            singleLine = inputControl.singleLine,
            label = { Text(text = label) },
            onValueChange = inputControl::onTextChanged,
            isError = inputControl.error != null,
            visualTransformation = inputControl.visualTransformation,
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    inputControl.onFocusChanged(it.isFocused)
                }
        )

        ErrorText(inputControl.error?.resolve() ?: "")
    }
}

@Composable
fun RowScope.ReminderButton(
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

@Composable
fun ErrorText(
    errorText: String,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 8.dp)
) {
    Text(
        modifier = modifier.padding(paddingValues),
        text = errorText,
        style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.error)
    )
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
    override val buttonsEnabled: Boolean = false

    override fun onWorkManagerClick() = Unit

    override fun onAlarmManagerClick() = Unit
}