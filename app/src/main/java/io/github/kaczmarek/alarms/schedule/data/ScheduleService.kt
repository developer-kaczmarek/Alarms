package io.github.kaczmarek.alarms.schedule.data

interface ScheduleService {

    fun setSchedule(title: String, description: String, repeatPeriod: Long)

    fun deleteSchedule()
}