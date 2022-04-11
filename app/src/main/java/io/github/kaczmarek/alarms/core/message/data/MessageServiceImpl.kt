package io.github.kaczmarek.alarms.core.message.data

import io.github.kaczmarek.alarms.core.message.domain.MessageData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MessageServiceImpl : MessageService {

    private val channel = Channel<MessageData>(Channel.UNLIMITED)

    override val messageFlow = channel.receiveAsFlow()

    override fun showMessage(message: MessageData) {
        channel.trySend(message)
    }
}