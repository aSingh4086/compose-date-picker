package com.liberty.android_compose_date_picker.components.datepicker.components.yearPicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liberty.android_compose_date_picker.components.BaseSurface
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.designTokens.DesignToken
import java.time.Year

@Composable
fun BaseYearPickerSpinner(
    startYear: Year,
    onClick: (Year) -> Unit,
    selectedYear: Year = Year.now(),
) {
    val startIndex = selectedYear.value - startYear.value
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)

    LazyColumn(
        state = lazyListState,
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(Int.MAX_VALUE) { year ->
            val minValue = startYear.value
            val nextValue = minValue + year
            YearSpinnerItem(Year.of(nextValue), onClick = onClick, isSelected = nextValue == selectedYear.value)
        }
    }
}

@Composable
fun YearSpinnerItem(year: Year, onClick: (Year) -> Unit, isSelected: Boolean = false) {
    BaseSurface(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            onClick(year)
        },
        backgroundColor = if (isSelected) DesignToken.ColorToken.color_aqua_haze else Color.White,
        contentColor = DesignToken.ColorToken.dark,
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            TextField(year.toString())
        }
    }
}

@Composable
@Preview
fun BaseYearPickerSpinnerPreview() {
    Box(modifier = Modifier.height(200.dp)) {
        BaseYearPickerSpinner(Year.of(1990), onClick = {})
    }
}
