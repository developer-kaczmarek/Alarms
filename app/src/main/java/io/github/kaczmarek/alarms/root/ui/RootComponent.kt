package io.github.kaczmarek.alarms.root.ui

import com.arkivanov.decompose.router.RouterState
import io.github.kaczmarek.alarms.core.message.ui.MessageComponent
import io.github.kaczmarek.alarms.reminder.ui.ReminderComponent

interface RootComponent {

    val messageComponent: MessageComponent

    val routerState: RouterState<*, Child>

    sealed interface Child {

        class Reminder(val component: ReminderComponent) : Child
    }
}