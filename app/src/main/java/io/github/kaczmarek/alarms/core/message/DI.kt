package io.github.kaczmarek.alarms.core.message

import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.core.message.ui.MessageComponent
import io.github.kaczmarek.alarms.core.message.ui.RealMessageComponent
import org.koin.core.component.get

fun ComponentFactory.createMessagesComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get())
}