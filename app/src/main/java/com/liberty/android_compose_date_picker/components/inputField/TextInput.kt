package com.liberty.android_compose_date_picker.components.inputField

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liberty.android_compose_date_picker.R
import com.liberty.android_compose_date_picker.components.baseInput.BaseInput
import com.liberty.android_compose_date_picker.components.baseInput.BaseInputErrorConfig
import com.liberty.android_compose_date_picker.components.baseInput.BaseInputType
import com.liberty.android_compose_date_picker.components.iconButton.IconButton
import com.liberty.android_compose_date_picker.components.iconButton.IconButtonImage


@Composable
fun TextInput(
    value: String? = "",
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit = {},
    label: String? = null,
    placeholder: String = "",
    isRequired: Boolean? = false,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    isLeftLabel: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    maxLength: Int = 124,
    errors: List<String> = emptyList(),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val errorConfig =
        BaseInputErrorConfig(
            errors = errors,
            isErrorVisible = errors.isNotEmpty(),
        )
    BaseInput(
        inputType = BaseInputType.Text,
        value = value,
        onValueChange = onValueChange,
        onFocusChange = onFocusChange,
        label = label,
        errorConfig = errorConfig,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = placeholder,
        isRequired = isRequired,
        isEnabled = isEnabled,
        isReadOnly = isReadOnly,
        isLeftLabel = isLeftLabel,
        interactionSource = interactionSource,
        maxLines = Int.MAX_VALUE,
    )
}

@Preview
@Composable
fun input() {
    TextInput(
        label = "Test",
        value = "Test",
        onValueChange = {},
        isEnabled = true,
        isReadOnly = false,
        leadingIcon = {
            IconButton(
                content = {
                    IconButtonImage(
                        modifier = Modifier,
                        imageId = R.drawable.icon_back_link_arrow,
                    )
                },
            )
        },
        trailingIcon = {
            IconButton(content = {
                IconButtonImage(
                    modifier = Modifier,
                    imageId = R.drawable.icon_back_link_arrow,
                )
            })
        },
    )
}
