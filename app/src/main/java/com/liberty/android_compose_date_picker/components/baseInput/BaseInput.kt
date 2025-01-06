package com.liberty.android_compose_date_picker.components.baseInput

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liberty.android_compose_date_picker.R
import com.liberty.android_compose_date_picker.components.LocalContentColor
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.components.iconButton.IconButton
import com.liberty.android_compose_date_picker.components.iconButton.IconButtonImage
import com.liberty.android_compose_date_picker.components.inputField.TextInput
import com.liberty.android_compose_date_picker.designTokens.DesignToken
import com.project.liberty.components.base.label.BaseLabel

data class BaseInputErrorConfig(
    val errors: List<String>,
    val errorComponent: @Composable () -> Unit = {},
    val isErrorVisible: Boolean = true,
)

@Composable
fun BaseInput(
    modifier: Modifier = Modifier,
    inputType: BaseInputType = BaseInputType.Text,
    value: String? = "",
    onValueChange: (value: String) -> Unit,
    onFocusChange: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    label: String? = null,
    placeholder: String = "",
    isRequired: Boolean? = false,
    isEnabled: Boolean = true,
    isFullArea: Boolean = false,
    isReadOnly: Boolean = false,
    isLeftLabel: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    maxLines: Int = 1,
    maxLength: Int = Int.MAX_VALUE,
    maskingEnabled: Boolean = false,
    errorConfig: BaseInputErrorConfig? =
        BaseInputErrorConfig(
            errors = emptyList(),
            isErrorVisible = false,
        ),
    isInteractive: Boolean = false,
    onClick: () -> Unit = {},
    isCursorVisible: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    val borderColor =
        if (isFocused && errorConfig?.isErrorVisible == false && errorConfig.errors.isEmpty()) {
            DesignToken.ColorToken.color_all_ports
        } else if (isFocused && errorConfig?.isErrorVisible == true && errorConfig.errors.isEmpty()) {
            DesignToken.ColorToken.color_all_ports

        } else if (errorConfig?.isErrorVisible == true && errorConfig.errors.isNotEmpty()) {
            DesignToken.ColorToken.color_crimson
        } else {
            DesignToken.BrandColorToken.brand_color_boulder
        }
    val borderWidth = if (isFocused) 2.dp else 1.dp

    fun handleValueChange(value: String) {
        when (inputType) {
            BaseInputType.Number -> {
                if (value.all { itemValue ->
                        itemValue.isDigit() ||
                                (itemValue == '.' && value.count { countValue -> countValue == '.' } == 1)
                    }
                ) {
                    onValueChange(
                        value,
                    )
                }
            }

            BaseInputType.Text -> onValueChange(value)
            BaseInputType.Select -> onValueChange(value)
            BaseInputType.Date -> onValueChange(value)
            else -> {
                onValueChange(value)
            }
        }
    }

    val focusRequester = remember { FocusRequester() }

    val basicTextField: @Composable () -> Unit = {
        BasicTextField(
            modifier =
            Modifier
                .focusable()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    onFocusChange(focusState.isFocused)
                },
            value = value ?: "",
            onValueChange = {
                if (maxLength > 0 && it.length > maxLength) {
                    handleValueChange(it)
                } else {
                    handleValueChange(it)
                }
            },
            textStyle =
            TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Left,
            ),
            maxLines = maxLines,
            interactionSource = interactionSource,
            singleLine = false,
            enabled = isEnabled,
            readOnly = isReadOnly,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            cursorBrush = if (isCursorVisible) SolidColor(Color.Black) else SolidColor(Color.Transparent),
            visualTransformation = if (maskingEnabled) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = @Composable { innerTextField ->
                // places leading icon, text field with label and placeholder, trailing icon
                BoxWithBorder(
                    modifier =
                    modifier
                        .minimumInteractiveComponentSize()
                        .border(
                            BorderStroke(borderWidth, borderColor),
                            shape = RoundedCornerShape(4.dp),
                        )
                        .then(
                            if (isInteractive) {
                                Modifier.clickable {
                                    onClick()
                                    focusRequester.requestFocus()
                                }
                            } else {
                                Modifier
                            },
                        ),
                    contentColor = Color.Black,
                    readOnly = isReadOnly,
                    enabled = isEnabled,
                    isFullArea = isFullArea,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                ) {
                    if (value?.isEmpty() == true && !isFocused) {
                        TextField(
                            text = placeholder,
                        )
                    } else {
                        innerTextField()
                    }
                }
            },
        )
    }

    val inputField: @Composable () -> Unit = {
        when (inputType) {
            BaseInputType.Select -> {
                CompositionLocalProvider(LocalTextInputService provides null) {
                    basicTextField()
                }
            }

            else -> {
                basicTextField()
            }
        }
    }

    val textField: @Composable () -> Unit = {
        label?.let {
            BaseLabel(
                label = it,
                isRequired = isRequired ?: false,
            )
        }
    }

    if (isLeftLabel) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            textField()
            inputField()
        }
    } else {
        Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
            if (label != null) {
                textField()
            }
            inputField()
        }
    }
}

@Composable
fun BoxWithBorder(
    modifier: Modifier = Modifier,
    contentColor: Color,
    enabled: Boolean,
    readOnly: Boolean,
    isFullArea: Boolean = false,
    trailingIcon: @Composable (() -> Unit)?,
    leadingIcon: @Composable (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier =
            modifier,
        ) {
            Row(
                modifier =
                Modifier
                    .height(if (isFullArea) 100.dp else 56.dp)
                    .padding(10.dp),
                verticalAlignment = if (isFullArea) Alignment.Top else Alignment.CenterVertically,
            ) {
                if (leadingIcon != null) {
                    Box(modifier = Modifier.weight(.1f)) {
                        leadingIcon()
                    }
                }
                Box(modifier = Modifier.weight(.8f)) {
                    content()
                }

                if (trailingIcon != null) {
                    Box(modifier = Modifier.weight(0.1f)) {
                        trailingIcon()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun input() {
    var text by remember {
        mutableStateOf("Test")
    }
    TextInput(
        value = text,
        onValueChange = {
            text = it
        },
        leadingIcon = {
            IconButton(content = {
                IconButtonImage(imageId = R.drawable.icon_calendar)
            }, onClick = {})
        },
        trailingIcon = {
            IconButton(content = {
                IconButtonImage(imageId = R.drawable.icon_calendar)
            }, onClick = {})
        },
    )
}
