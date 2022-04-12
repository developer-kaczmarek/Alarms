package io.github.kaczmarek.alarms.menu.ui

import com.arkivanov.decompose.ComponentContext

class RealMenuComponent(
    componentContext: ComponentContext,
    private val onOutput: (MenuComponent.Output) -> Unit
) : ComponentContext by componentContext, MenuComponent {

    override fun onSetScheduleClick() {
        onOutput(MenuComponent.Output.ScheduleRequested)
    }

    override fun onSetReminderClick() {
        onOutput(MenuComponent.Output.ReminderRequested)
    }
}