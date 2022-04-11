package io.github.kaczmarek.alarms.core.error_handling

abstract class ApplicationException(cause: Throwable? = null) : Exception(cause)

/**
 * Какая-то неизвестная проблема
 */
class UnknownException(cause: Throwable, override val message: String) : ApplicationException(cause)