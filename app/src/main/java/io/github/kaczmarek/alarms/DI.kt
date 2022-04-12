package io.github.kaczmarek.alarms

import io.github.kaczmarek.alarms.core.coreModule
import io.github.kaczmarek.alarms.reminder.reminderModule
import io.github.kaczmarek.alarms.schedule.scheduleModule

val allModules = listOf(
    coreModule,
    scheduleModule,
    reminderModule
)