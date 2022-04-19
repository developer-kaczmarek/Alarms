package io.github.kaczmarek.alarms

import io.github.kaczmarek.alarms.core.coreModule
import io.github.kaczmarek.alarms.reminder.reminderModule

val allModules = listOf(
    coreModule,
    reminderModule
)