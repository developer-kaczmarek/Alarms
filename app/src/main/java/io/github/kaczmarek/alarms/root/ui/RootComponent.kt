package io.github.kaczmarek.alarms.root.ui

import com.arkivanov.decompose.router.RouterState
import io.github.kaczmarek.alarms.core.message.ui.MessageComponent
import io.github.kaczmarek.alarms.menu.ui.MenuComponent
import io.github.kaczmarek.alarms.reminder.ui.ReminderComponent
import io.github.kaczmarek.alarms.schedule.ui.ScheduleComponent

interface RootComponent {

    val messageComponent: MessageComponent

    val routerState: RouterState<*, Child>

    sealed interface Child {

        class Schedule(val component: ScheduleComponent) : Child

        class Menu(val component: MenuComponent) : Child

        class Reminder(val component: ReminderComponent) : Child
    }
}