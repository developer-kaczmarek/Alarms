package io.github.kaczmarek.alarms.reminder.ui

import com.arkivanov.decompose.ComponentContext

class RealReminderComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, ReminderComponent