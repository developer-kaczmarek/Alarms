package io.github.kaczmarek.alarms.root.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import io.github.kaczmarek.alarms.core.message.ui.FakeMessageComponent
import io.github.kaczmarek.alarms.core.message.ui.MessageUi
import io.github.kaczmarek.alarms.core.theme.AppTheme
import io.github.kaczmarek.alarms.core.utils.createFakeRouterState
import io.github.kaczmarek.alarms.menu.ui.FakeMenuComponent
import io.github.kaczmarek.alarms.menu.ui.MenuUi
import io.github.kaczmarek.alarms.reminder.ui.ReminderUi
import io.github.kaczmarek.alarms.schedule.ui.ScheduleUi

@Composable
fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    AppTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Box {
                Children(component.routerState) { child ->
                    when (val instance = child.instance) {
                        is RootComponent.Child.Menu -> MenuUi(instance.component)
                        is RootComponent.Child.Schedule -> ScheduleUi(instance.component)
                        is RootComponent.Child.Reminder -> ReminderUi(instance.component)
                    }
                }

                MessageUi(component = component.messageComponent, bottomPadding = 16.dp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RootUiPreview() {
    AppTheme {
        RootUi(FakeRootComponent())
    }
}

class FakeRootComponent : RootComponent {

    override val messageComponent = FakeMessageComponent()

    override val routerState = createFakeRouterState(
        RootComponent.Child.Menu(FakeMenuComponent())
    )
}