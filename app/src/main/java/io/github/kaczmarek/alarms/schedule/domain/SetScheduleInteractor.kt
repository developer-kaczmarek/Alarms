package io.github.kaczmarek.alarms.schedule.domain

import io.github.kaczmarek.alarms.schedule.data.ScheduleService

class SetScheduleInteractor(private val scheduleService: ScheduleService) {

    fun execute(title: String, description: String, repeatPeriod: Long) {
        scheduleService.setSchedule(title, description, repeatPeriod)
    }
}