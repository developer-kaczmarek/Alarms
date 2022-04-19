package io.github.kaczmarek.alarms.core.widget.dialog

sealed class DialogResult {
    class Confirm(val dateTime: DateTime) : DialogResult()

    object Cancel : DialogResult()
}