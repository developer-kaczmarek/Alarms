package io.github.kaczmarek.alarms.core.message.ui

import io.github.kaczmarek.alarms.core.message.domain.MessageData

interface MessageComponent {

    val visibleMessageData: MessageData?
}