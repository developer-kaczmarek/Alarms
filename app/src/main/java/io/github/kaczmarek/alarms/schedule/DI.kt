package io.github.kaczmarek.alarms.schedule

import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.core.ComponentFactory
import io.github.kaczmarek.alarms.schedule.data.ScheduleService
import io.github.kaczmarek.alarms.schedule.data.ScheduleServiceImpl
import io.github.kaczmarek.alarms.schedule.domain.DeleteSchedulesInteractor
import io.github.kaczmarek.alarms.schedule.domain.SetScheduleInteractor
import io.github.kaczmarek.alarms.schedule.ui.RealScheduleComponent
import io.github.kaczmarek.alarms.schedule.ui.ScheduleComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.get
import org.koin.dsl.module

val scheduleModule = module {
    single<ScheduleService> { ScheduleServiceImpl(androidContext()) }
    factory { DeleteSchedulesInteractor(get()) }
    factory { SetScheduleInteractor(get()) }
}

fun ComponentFactory.createScheduleComponent(componentContext: ComponentContext): ScheduleComponent {
    return RealScheduleComponent(componentContext, get(), get(), get(), get())
}