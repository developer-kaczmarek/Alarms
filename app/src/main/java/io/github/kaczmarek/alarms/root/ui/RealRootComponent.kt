package io.github.kaczmarek.alarms.root.ui

import android.os.Parcelable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.core.message.createMessagesComponent
import io.github.kaczmarek.alarms.core.utils.toComposeState
import io.github.kaczmarek.alarms.reminder.createReminderComponent
import kotlinx.parcelize.Parcelize

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val router = router<ChildConfig, RootComponent.Child>(
        initialConfiguration = ChildConfig.Reminder,
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
            is ChildConfig.Reminder -> RootComponent.Child.Reminder(
                componentFactory.createReminderComponent(componentContext)
            )
        }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Reminder : ChildConfig
    }
}