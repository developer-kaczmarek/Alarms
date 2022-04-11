package io.github.kaczmarek.alarms.core

import io.github.kaczmarek.alarms.core.error_handling.ErrorHandler
import io.github.kaczmarek.alarms.core.message.data.MessageService
import io.github.kaczmarek.alarms.core.message.data.MessageServiceImpl
import org.koin.dsl.module

val coreModule = module {
    single { ActivityProvider() }
    single<MessageService> { MessageServiceImpl() }
    single { ErrorHandler(get()) }
}