package com.project.liberty.components.base.label

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.designTokens.DesignToken
import java.util.Locale

@Composable
fun BaseLabel(
    label: String = "",
    isRequired: Boolean = false,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(1.dp)) {
        TextField(
            text = label.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            color = DesignToken.BrandColorToken.brand_color_dove_gray,
            fontSize = 16.sp,
        )
        if (isRequired) {
            TextField("*", color = DesignToken.ColorToken.color_chelsea_gem)
        }
    }
}

@Preview
@Composable
fun PreviewBaseLabel() {
    BaseLabel("label", isRequired = true)
}
