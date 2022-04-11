package io.github.kaczmarek.alarms.core.message.data

import io.github.kaczmarek.alarms.core.message.domain.MessageData
import kotlinx.coroutines.flow.Flow

interface MessageService {

    val messageFlow: Flow<MessageData>

    fun showMessage(message: MessageData)
}