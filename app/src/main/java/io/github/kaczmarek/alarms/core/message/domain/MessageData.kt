package io.github.kaczmarek.alarms.core.message.domain

import androidx.annotation.DrawableRes
import me.aartikov.sesame.localizedstring.LocalizedString
import java.util.*

data class MessageData(
    val text: LocalizedString,
    @DrawableRes val iconRes: Int? = null
)