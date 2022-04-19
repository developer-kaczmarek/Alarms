package io.github.kaczmarek.alarms.menu

import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.menu.ui.MenuComponent
import io.github.kaczmarek.alarms.menu.ui.RealMenuComponent

fun ComponentFactory.createMenuComponent(
    componentContext: ComponentContext,
    onOutput: (MenuComponent.Output) -> Unit
): MenuComponent {
    return RealMenuComponent(componentContext, onOutput)
}
