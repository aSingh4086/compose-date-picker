package com.liberty.eventcalendar.eventsCalendar.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.liberty.android_compose_date_picker.components.ButtonColors
import com.liberty.android_compose_date_picker.designTokens.DesignToken

object ButtonDefaults {
    val MinWidth = 58.dp
    val MinHeight = 40.dp
    val Primary = DesignToken.ColorToken.sys_color_theme_primary
    val Secondary = DesignToken.ColorToken.color_secondary
    val Disabled = DesignToken.ColorToken.sys_color_rowHighlight
    val NONE = Color.White
    val ContentColor = Color.White

    private val ButtonHorizontalPadding = 24.dp
    private val ButtonVerticalPadding = 8.dp

    val ContentPadding =
        PaddingValues(
            start = ButtonHorizontalPadding,
            top = ButtonVerticalPadding,
            end = ButtonHorizontalPadding,
            bottom = ButtonVerticalPadding,
        )

    fun defaultColors(): ButtonColors {
        return ButtonColors(
            backgroundColor = Primary,
            contentColor = ContentColor,
            borderColor = Primary,
        )
    }

    fun secondaryColors(): ButtonColors {
        return ButtonColors(
            backgroundColor = Secondary,
            contentColor = ContentColor,
            borderColor = Secondary,
        )
    }

    fun textColors(): ButtonColors {
        return ButtonColors(
            backgroundColor = NONE,
            contentColor = Color.Blue,
            borderColor = NONE,
        )
    }

    fun noPresetStylesColors(): ButtonColors {
        return ButtonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White,
            borderColor = Color.Black,
        )
    }
}

enum class ButtonAccessType {
    PRIMARY,
    SECONDARY,
    TEXT_ONLY,
    NONE,
}
