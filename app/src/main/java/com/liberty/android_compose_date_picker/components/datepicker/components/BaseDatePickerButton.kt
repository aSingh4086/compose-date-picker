package com.liberty.android_compose_date_picker.components.datepicker.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.designTokens.DesignToken


@Composable
fun BaseDatePickerButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val backgroundColor by animateColorAsState(
        targetValue =
            if (isSelected) {
                DesignToken.ColorToken.color_cornflower_blue
            } else {
                Color.Transparent
            },
        label = "",
        animationSpec = tween(durationMillis = 600),
    )
    Box(
        modifier =
            modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Min)
                .clip(CircleShape)
                .background(
                    if (isSelected) backgroundColor else Color.White,
                    shape = CircleShape,
                ).clickable {
                    onClick()
                },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview
@Composable
fun BaseDatePickerButtonPreview() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        BaseDatePickerButton(
            modifier = Modifier,
            isSelected = true,
            onClick = {},
        ) {
            TextField("test")
        }
    }
}
