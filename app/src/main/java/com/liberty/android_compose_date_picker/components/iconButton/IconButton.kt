package com.liberty.android_compose_date_picker.components.iconButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liberty.android_compose_date_picker.R
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.designTokens.DesignToken


@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = {},
    content: (@Composable () -> Unit)? = null,
) {
    var pressed by remember { mutableStateOf(false) }
    LaunchedEffect(interactionSource.interactions) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is androidx.compose.foundation.interaction.PressInteraction.Press -> pressed = true
                is androidx.compose.foundation.interaction.PressInteraction.Release ->
                    pressed =
                        false

                else -> {}
            }
        }
    }

    if (content != null) {
        modifier.clip(RoundedCornerShape(50.dp))
    } else {
        modifier.clip(CircleShape)
    }
    Box(
        modifier =
        modifier
            .background(
                color =
                if (pressed) {
                    DesignToken.ColorToken.sys_color_success.copy(alpha = 0.2f)
                } else {
                    Color.Transparent
                },
                shape = CircleShape,
            )
            .minimumInteractiveComponentSize()
            .padding(10.dp)
            .clickable(
                onClick = onClick,
                enabled = isEnabled,
                interactionSource = interactionSource,
                indication = null, // Disable default ripple effect
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (content != null) {
                content()
            }
        }
    }
}

@Composable
fun IconButtonImage(
    modifier: Modifier = Modifier,
    imageId: Int,
) {
    Image(
        imageVector = ImageVector.vectorResource(id = imageId),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier.size(16.dp),
    )
}

@Preview
@Composable
fun preview() {
    IconButton(
        content = {
            IconButtonImage(imageId = R.drawable.icon_warning)
            TextField("test")
        },
    )
}
