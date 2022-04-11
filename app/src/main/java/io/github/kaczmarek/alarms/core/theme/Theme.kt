package io.github.kaczmarek.alarms.core.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import io.github.kaczmarek.alarms.core.message.ui.LocalMessageOffsets

@SuppressLint("UnrememberedMutableState")
@Composable
fun AppTheme(content: @Composable () -> Unit) {

    val messageOffsets = remember { mutableStateMapOf<Int, Int>() }

    CompositionLocalProvider(
        LocalMessageOffsets provides messageOffsets
    ) {
        MaterialTheme(
            colors = getMaterialLightColors(),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}