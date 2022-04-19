package io.github.kaczmarek.alarms.menu.ui

interface MenuComponent {

    sealed interface Output {
        object ReminderRequested : Output

        object ScheduleRequested : Output
    }

    fun onSetScheduleClick()

    fun onSetReminderClick()
}