package io.github.kaczmarek.alarms.root

import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.root.ui.RealRootComponent
import io.github.kaczmarek.alarms.root.ui.RootComponent
import org.koin.core.component.get

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(componentContext, get())
}