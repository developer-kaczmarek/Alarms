package io.github.kaczmarek.alarms.reminder.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kaczmarek.alarms.core.theme.AppTheme

@Composable
fun ReminderUi(
    component: ReminderComponent,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Reminder UI")
    }
}

@Preview(showSystemUi = true)
@Composable
fun ReminderUiPreview() {
    AppTheme {
        ReminderUi(FakeReminderComponent())
    }
}

class FakeReminderComponent : ReminderComponent