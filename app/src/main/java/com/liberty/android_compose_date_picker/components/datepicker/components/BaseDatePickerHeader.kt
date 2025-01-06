package com.liberty.android_compose_date_picker.components.datepicker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liberty.android_compose_date_picker.R
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.components.iconButton.IconButton
import com.liberty.android_compose_date_picker.components.iconButton.IconButtonImage
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun BaseDatePickerHeader(
    currentYearMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onClickYear: () -> Unit,
) {
    val selectedYearMonth = currentYearMonth.year
    val selectedMonthDisplayName =
        currentYearMonth.month.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault(),
        )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(content = {
            IconButtonImage(imageId = R.drawable.icon_back_link_arrow)
        }, onClick = {
            onPreviousMonth()
        })
        IconButton(onClick = onClickYear, modifier = Modifier) {
            TextField(
                text = "$selectedMonthDisplayName $selectedYearMonth",
                modifier = Modifier
            )
        }
        IconButton(content = {
            IconButtonImage(imageId = R.drawable.icon_link_arrow)
        }, onClick = {
            onNextMonth()
        })
    }
}


@Composable
@Preview
fun BaseDatePickerHeaderPreview() {
    BaseDatePickerHeader(
        currentYearMonth = YearMonth.now(),
        onPreviousMonth = {},
        onNextMonth = {},
        onClickYear = {}
    )
}