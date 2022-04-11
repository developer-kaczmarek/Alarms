package io.github.kaczmarek.alarms.core.message.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import io.github.kaczmarek.alarms.core.message.data.MessageService
import io.github.kaczmarek.alarms.core.message.domain.MessageData
import io.github.kaczmarek.alarms.core.utils.componentCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RealMessageComponent(
    componentContext: ComponentContext,
    private val messageService: MessageService,
) : ComponentContext by componentContext, MessageComponent {

    companion object {
        private const val ShowTime = 4000L
    }

    private val coroutineScope = componentCoroutineScope()

    override var visibleMessageData by mutableStateOf<MessageData?>(null)
        private set

    private var autoDismissJob: Job? = null

    init {
        lifecycle.doOnCreate(::collectMessages)
    }

    private fun collectMessages() {
        coroutineScope.launch {
            messageService.messageFlow.collect {
                showMessage(it)
            }
        }
    }

    private fun showMessage(messageData: MessageData) {
        autoDismissJob?.cancel()
        visibleMessageData = messageData
        autoDismissJob = coroutineScope.launch {
            delay(ShowTime)
            visibleMessageData = null
        }
    }
}