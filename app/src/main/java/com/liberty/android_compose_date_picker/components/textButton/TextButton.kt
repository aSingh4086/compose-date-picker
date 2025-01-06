package com.liberty.android_compose_date_picker.components.textButton

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.liberty.android_compose_date_picker.components.Button
import com.liberty.eventcalendar.eventsCalendar.components.ButtonAccessType
import com.liberty.android_compose_date_picker.components.TextField

@Composable
fun TextButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        content = content,
        onClick = onClick,
        shape = CircleShape,
        accessType = ButtonAccessType.TEXT_ONLY,
    )
}

@Preview
@Composable
fun TextButtonPreview() {
    TextButton(
        onClick = {},
        content = {
            TextField("Text Button")
        },
    )
}
