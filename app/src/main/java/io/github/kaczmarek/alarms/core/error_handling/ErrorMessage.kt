package io.github.kaczmarek.alarms.core.error_handling

import io.github.kaczmarek.alarms.R
import me.aartikov.sesame.localizedstring.LocalizedString

val Throwable.errorMessage: LocalizedString
    get() = LocalizedString.resource(
        R.string.error_unexpected_with_description,
        this.message ?: ""
    )