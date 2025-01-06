package com.liberty.android_compose_date_picker.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liberty.eventcalendar.eventsCalendar.components.ButtonAccessType
import com.liberty.eventcalendar.eventsCalendar.components.ButtonDefaults
import com.liberty.android_compose_date_picker.designTokens.DesignToken

data class BaseInputStyle(
    val contentColor: Color = DesignToken.FontColorToken.liberty_color_titan_white,
    val backgroundColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val shape: Shape = RoundedCornerShape(40.dp),
)

@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(40.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    accessType: ButtonAccessType = ButtonAccessType.PRIMARY,
    content: @Composable RowScope.() -> Unit,
) {
    val colors: ButtonColors =
        when (accessType) {
            ButtonAccessType.PRIMARY -> ButtonDefaults.defaultColors()
            ButtonAccessType.SECONDARY -> ButtonDefaults.secondaryColors()
            ButtonAccessType.TEXT_ONLY -> ButtonDefaults.textColors()
            ButtonAccessType.NONE -> ButtonDefaults.noPresetStylesColors()
        }

    val styles =
        BaseInputStyle(
            contentColor = colors.getContentColor(enabled),
            backgroundColor = colors.getBackgroundColor(enabled),
            borderColor = colors.getBorderColor(enabled),
            shape = shape,
        )

    BaseSurface(
        onClick = onClick,
        modifier =
            modifier
                .minimumInteractiveComponentSize()
                .semantics {
                    role = Role.Button
                    contentDescription = "Button"
                },
        enabled = enabled,
        shape = styles.shape,
        contentColor = styles.contentColor,
        backgroundColor = styles.backgroundColor,
        border = BorderStroke(1.dp, styles.borderColor),
        interactionSource = interactionSource,
    ) {
        Row(
            Modifier
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight,
                ).padding(ButtonDefaults.ContentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

@Preview
@Composable
fun ButtonDemo() {
    Button(
        accessType = ButtonAccessType.TEXT_ONLY,
        onClick = { /*TODO*/ },
    ) {
        TextField(text = "Test")
    }
}
