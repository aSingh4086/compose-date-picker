package com.liberty.android_compose_date_picker.components

import androidx.compose.ui.graphics.Color

class ButtonColors constructor(
    val backgroundColor: Color,
    private val contentColor: Color,
    private val borderColor: Color,
) {
    fun getBackgroundColor(enabled: Boolean): Color {
        if (!enabled) {
            return backgroundColor
        }

        return backgroundColor
    }

    fun getContentColor(enabled: Boolean): Color {
        if (!enabled) {
            return contentColor
        }

        return contentColor
    }

    fun getBorderColor(enabled: Boolean): Color {
        if (!enabled) {
            return borderColor
        }

        return borderColor
    }
}
