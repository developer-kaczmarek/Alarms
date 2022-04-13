package io.github.kaczmarek.alarms.reminder.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.error_handling.ErrorHandler
import io.github.kaczmarek.alarms.core.error_handling.safeLaunch
import io.github.kaczmarek.alarms.core.error_handling.safeRun
import io.github.kaczmarek.alarms.core.utils.componentCoroutineScope
import io.github.kaczmarek.alarms.core.widget.dialog.DatePickerDialogData
import io.github.kaczmarek.alarms.core.widget.dialog.DialogResult
import io.github.kaczmarek.alarms.core.widget.dialog.TimePickerDialogData
import io.github.kaczmarek.alarms.reminder.domain.DeleteReminderInteractor
import io.github.kaczmarek.alarms.reminder.domain.SetReminderInteractor
import me.aartikov.sesame.compose.form.control.InputControl
import me.aartikov.sesame.compose.form.validation.control.isNotBlank
import me.aartikov.sesame.compose.form.validation.form.*
import me.aartikov.sesame.dialog.DialogControl
import java.util.*
import java.util.Calendar.SECOND

class RealReminderComponent(
    componentContext: ComponentContext,
    private val errorHandler: ErrorHandler,
    private val setReminderInteractor: SetReminderInteractor,
    private val deleteReminderInteractor: DeleteReminderInteractor
) : ComponentContext by componentContext, ReminderComponent {

    private val coroutineScope = componentCoroutineScope()

    private val calendar = Calendar.getInstance().apply {
        this[SECOND] = 0
    }

    override val titleInput = InputControl(
        maxLength = 30,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
    )

    override val descriptionInput = InputControl(
        maxLength = 50,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
    )

    private val formValidator = coroutineScope.formValidator {
        features = listOf(
            ValidateOnFocusLost,
            RevalidateOnValueChanged,
            SetFocusOnFirstInvalidControlAfterValidation
        )

        input(titleInput) {
            isNotBlank(R.string.error_field_is_blank_error_message)
        }

        input(descriptionInput) {
            isNotBlank(R.string.error_field_is_blank_error_message)
        }
    }

    private val dynamicResult by coroutineScope.dynamicValidationResult(formValidator)

    override val setReminderButtonEnabled by derivedStateOf {
        dynamicResult.isValid
    }

    override val timePickerDialogControl = DialogControl<TimePickerDialogData, DialogResult>()

    override val datePickerDialogControl = DialogControl<DatePickerDialogData, DialogResult>()

    override fun onSetReminderClick() {
        safeRun(errorHandler) {
            setReminderInteractor.execute(
                title = titleInput.text,
                description = descriptionInput.text,
                timeInMillis = calendar.timeInMillis
            )
            titleInput.onTextChanged("")
            descriptionInput.onTextChanged("")
        }
    }

    override fun onDeleteReminderClick() {
        safeRun(errorHandler) {
            deleteReminderInteractor.execute()
        }
    }

    override fun onShowTimePickerDialogClick() {
        coroutineScope.safeLaunch(errorHandler) {
            val result = timePickerDialogControl.showForResult(
                TimePickerDialogData(
                    hourOfDay = calendar[Calendar.HOUR_OF_DAY],
                    minute = calendar[Calendar.MINUTE]
                )
            ) ?: return@safeLaunch

            if (result is DialogResult.Confirm) {
                calendar[Calendar.HOUR_OF_DAY] = result.dateTime.hours
                calendar[Calendar.MINUTE] = result.dateTime.minute
            }
        }
    }

    override fun onShowDatePickerDialogClick() {
        coroutineScope.safeLaunch(errorHandler) {
            val result = datePickerDialogControl.showForResult(
                DatePickerDialogData(
                    month = calendar[Calendar.MONTH],
                    year = calendar[Calendar.YEAR],
                    dayOfMonth = calendar[Calendar.DAY_OF_MONTH],
                )
            ) ?: return@safeLaunch

            if (result is DialogResult.Confirm) {
                calendar[Calendar.YEAR] = result.dateTime.year
                calendar[Calendar.MONTH] = result.dateTime.month
            }
        }
    }
}