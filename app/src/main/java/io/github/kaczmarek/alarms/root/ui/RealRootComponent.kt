package io.github.kaczmarek.alarms.root.ui

import android.os.Parcelable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.core.message.createMessagesComponent
import io.github.kaczmarek.alarms.core.utils.toComposeState
import io.github.kaczmarek.alarms.menu.createMenuComponent
import io.github.kaczmarek.alarms.menu.ui.MenuComponent
import io.github.kaczmarek.alarms.reminder.createReminderComponent
import io.github.kaczmarek.alarms.schedule.createScheduleComponent
import kotlinx.parcelize.Parcelize

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val router = router<ChildConfig, RootComponent.Child>(
        initialConfiguration = ChildConfig.Menu,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val routerState: RouterState<*, RootComponent.Child> by router.state.toComposeState(
        lifecycle
    )

    override val messageComponent =
        componentFactory.createMessagesComponent(childContext(key = "message"))

    private fun createChild(config: ChildConfig, componentContext: ComponentContext) =
        when (config) {
            is ChildConfig.Menu -> RootComponent.Child.Menu(
                componentFactory.createMenuComponent(componentContext, ::onMenuOutput)
            )
            is ChildConfig.Schedule -> RootComponent.Child.Schedule(
                componentFactory.createScheduleComponent(componentContext)
            )
            is ChildConfig.Reminder -> RootComponent.Child.Reminder(
                componentFactory.createReminderComponent(componentContext)
            )
        }

    private fun onMenuOutput(output: MenuComponent.Output) {
        when (output) {
            is MenuComponent.Output.ScheduleRequested -> {
                router.push(ChildConfig.Schedule)
            }

            is MenuComponent.Output.ReminderRequested -> {
                router.push(ChildConfig.Reminder)
            }
        }
    }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Schedule : ChildConfig

        @Parcelize
        object Menu : ChildConfig

        @Parcelize
        object Reminder : ChildConfig
    }
}