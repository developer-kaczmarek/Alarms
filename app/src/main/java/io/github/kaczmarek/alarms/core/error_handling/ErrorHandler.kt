package io.github.kaczmarek.alarms.core.error_handling

import io.github.kaczmarek.alarms.core.message.data.MessageService
import io.github.kaczmarek.alarms.core.message.domain.MessageData

class ErrorHandler(private val messageService: MessageService) {

    fun handleError(throwable: Throwable, showError: Boolean = true) {
        when {
            showError -> {
                messageService.showMessage(MessageData(text = throwable.errorMessage))
            }
        }
    }
}