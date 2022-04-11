package io.github.kaczmarek.alarms.reminder

import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.reminder.ui.RealReminderComponent
import io.github.kaczmarek.alarms.reminder.ui.ReminderComponent
import org.koin.core.component.get

fun ComponentFactory.createReminderComponent(componentContext: ComponentContext): ReminderComponent {
    return RealReminderComponent(componentContext)
}