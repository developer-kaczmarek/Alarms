package io.github.kaczmarek.alarms.core

import io.github.kaczmarek.alarms.core.message.data.MessageService
import io.github.kaczmarek.alarms.core.message.data.MessageServiceImpl
import org.koin.dsl.module

val coreModule = module {
    single { ActivityProvider() }
    single<MessageService> { MessageServiceImpl() }
}