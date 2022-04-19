package io.github.kaczmarek.alarms.menu.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.theme.AppTheme

@Composable
fun MenuUi(
    component: MenuComponent,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        MenuButton(
            text = stringResource(R.string.menu_set_reminder),
            onClick = component::onSetReminderClick
        )
        MenuButton(
            text = stringResource(R.string.menu_set_schedule),
            onClick = component::onSetScheduleClick,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Composable
fun MenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = onClick
    ) {
        Text(
            text = text.uppercase(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MenuUiPreview() {
    AppTheme {
        MenuUi(FakeMenuComponent())
    }
}

class FakeMenuComponent : MenuComponent {

    override fun onSetScheduleClick() = Unit

    override fun onSetReminderClick() = Unit
}