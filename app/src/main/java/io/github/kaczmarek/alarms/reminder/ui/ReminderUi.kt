package io.github.kaczmarek.alarms.reminder.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaczmarek.alarms.core.theme.AppTheme

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
            Text(text = "ReminderUi")
        }
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