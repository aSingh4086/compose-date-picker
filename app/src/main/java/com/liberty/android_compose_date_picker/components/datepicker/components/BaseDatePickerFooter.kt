package com.liberty.android_compose_date_picker.components.datepicker.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.liberty.android_compose_date_picker.R
import com.liberty.android_compose_date_picker.components.Button
import com.liberty.android_compose_date_picker.components.TextField


@Composable
fun BaseDatePickerFooter(onClose: () -> Unit) {
    Box(
        modifier = Modifier,
    ) {
        Button(onClick = onClose) {
            TextField(text = stringResource(id = R.string.cancel))
        }
        Button(onClick = { /*TODO*/ }) {
            TextField(text = "ok")
        }
    }
}

@Preview(name = "BaseDatePickerFooter")
@Composable
private fun PreviewBaseDatePickerFooter() {
    BaseDatePickerFooter {}
}
