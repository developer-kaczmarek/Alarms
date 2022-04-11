package io.github.kaczmarek.alarms.reminder.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.utils.componentCoroutineScope
import me.aartikov.sesame.compose.form.control.InputControl
import me.aartikov.sesame.compose.form.validation.control.isNotBlank
import me.aartikov.sesame.compose.form.validation.form.*

class RealReminderComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, ReminderComponent {

    private val coroutineScope = componentCoroutineScope()

    override val titleInput = InputControl(
        maxLength = 30,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )

    override val descriptionInput = InputControl(
        maxLength = 50,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )

    private val formValidator = coroutineScope.formValidator {

        features = listOf(
            ValidateOnFocusLost,
            RevalidateOnValueChanged,
            SetFocusOnFirstInvalidControlAfterValidation
        )

        input(titleInput) {
            isNotBlank(R.string.reminder_field_is_blank_error_message)
        }

        input(descriptionInput) {
            isNotBlank(R.string.reminder_field_is_blank_error_message)
        }
    }

    private val dynamicResult by coroutineScope.dynamicValidationResult(formValidator)

    override val buttonsEnabled by derivedStateOf {
        dynamicResult.isValid
    }

    override fun onWorkManagerClick() {
        // TODO: создать задачу через WorkManager
    }

    override fun onAlarmManagerClick() {
        // TODO: создать задачу через AlarmManager
    }
}