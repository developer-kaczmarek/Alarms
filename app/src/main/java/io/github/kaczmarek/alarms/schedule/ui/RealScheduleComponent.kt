package io.github.kaczmarek.alarms.schedule.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.arkivanov.decompose.ComponentContext
import io.github.kaczmarek.alarms.R
import io.github.kaczmarek.alarms.core.error_handling.ErrorHandler
import io.github.kaczmarek.alarms.core.error_handling.safeRun
import io.github.kaczmarek.alarms.core.utils.componentCoroutineScope
import io.github.kaczmarek.alarms.schedule.domain.DeleteSchedulesInteractor
import io.github.kaczmarek.alarms.schedule.domain.SetScheduleInteractor
import me.aartikov.sesame.compose.form.control.InputControl
import me.aartikov.sesame.compose.form.validation.control.isNotBlank
import me.aartikov.sesame.compose.form.validation.form.*

class RealScheduleComponent(
    componentContext: ComponentContext,
    private val errorHandler: ErrorHandler,
    private val setScheduleInteractor: SetScheduleInteractor,
    private val deleteSchedulesInteractor: DeleteSchedulesInteractor
) : ComponentContext by componentContext, ScheduleComponent {

    private val coroutineScope = componentCoroutineScope()

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

    override val setScheduleButtonEnabled by derivedStateOf {
        dynamicResult.isValid
    }

    override fun onSetScheduleClick() {
        safeRun(errorHandler) {
            setScheduleInteractor.execute(
                title = titleInput.text,
                description = descriptionInput.text,
                repeatPeriod = 30L
            )
            titleInput.onTextChanged("")
            descriptionInput.onTextChanged("")
        }
    }

    override fun onDeleteSchedulesClick() {
        safeRun(errorHandler) {
            deleteSchedulesInteractor.execute()
        }
    }
}