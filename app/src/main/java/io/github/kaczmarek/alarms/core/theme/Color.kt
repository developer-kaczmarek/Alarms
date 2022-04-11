package io.github.kaczmarek.alarms.core.theme

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object RawColors {
    val white: Color = Color(0xFFFFFFFF)
    val gray: Color = Color(0xFFF3F4F7)
    val grayA: Color = Color(0xFFA1ACB4)
    val black: Color = Color(0xFF000000)
    val blackA: Color = Color(0xFF121212)
    val blueA: Color = Color(0xFF009DE0)
    val blueB: Color = Color(0xFF01D4E4)
}

fun getMaterialLightColors(): Colors {
    return lightColors(
        primary = RawColors.blueA,
        primaryVariant = RawColors.blueB,
        secondary = RawColors.blueA,
        secondaryVariant = RawColors.blueB,
        background = RawColors.white,
        surface = RawColors.gray,
        onPrimary = RawColors.white,
        onSecondary = RawColors.white,
        onBackground = RawColors.black,
        onSurface = RawColors.grayA
    )
}