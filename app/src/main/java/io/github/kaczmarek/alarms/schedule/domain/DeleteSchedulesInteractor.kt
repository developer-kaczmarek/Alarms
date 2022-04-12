package io.github.kaczmarek.alarms.schedule.domain

import io.github.kaczmarek.alarms.schedule.data.ScheduleService

class DeleteSchedulesInteractor(private val scheduleService: ScheduleService) {

    fun execute() {
        scheduleService.deleteSchedule()
    }
}