package io.github.kaczmarek.alarms.reminder

import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.reminder.data.ReminderService
import io.github.kaczmarek.alarms.reminder.data.ReminderServiceImpl
import io.github.kaczmarek.alarms.reminder.domain.DeleteReminderInteractor
import io.github.kaczmarek.alarms.reminder.domain.SetReminderInteractor
import io.github.kaczmarek.alarms.reminder.ui.RealReminderComponent
import io.github.kaczmarek.alarms.reminder.ui.ReminderComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.get
import org.koin.dsl.module

val reminderModule = module {
    single<ReminderService> { ReminderServiceImpl(androidContext()) }
    factory { SetReminderInteractor(get()) }
    factory { DeleteReminderInteractor(get()) }
}

fun ComponentFactory.createReminderComponent(componentContext: ComponentContext): ReminderComponent {
    return RealReminderComponent(componentContext, get(), get(), get())
}