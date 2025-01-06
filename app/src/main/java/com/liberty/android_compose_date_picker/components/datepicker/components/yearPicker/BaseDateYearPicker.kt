package com.liberty.android_compose_date_picker.components.datepicker.components.yearPicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.components.datepicker.components.BaseDatePickerButton
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun BaseDateYearPicker(
    modifier: Modifier = Modifier,
    localDate: LocalDate = LocalDate.now(),
    onMonthSelection: () -> Unit = {},
    onYearSelection: () -> Unit = {},
) {
    var isYearMode by remember { mutableStateOf(true) }
    val listOfYears by remember {
        derivedStateOf {
            val years = mutableListOf<Int>()
            for (i in 0..11) {
                years.add(localDate.year - i)
            }
            years
        }
    }

    val listOfMonthsByDisplayName by remember {
        derivedStateOf {
            val months = mutableListOf<String>()
            for (i in 1..12) {
                months.add(
                    localDate.withMonth(i).month.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault(),
                    ),
                )
            }
            months
        }
    }

    val modeLabel =
        if (isYearMode) "${localDate.year}-${localDate.year - 10}" else localDate.year.toString()

    Box(modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier =
                    Modifier.clickable {
                        isYearMode = !isYearMode
                    },
            ) {
                TextField(text = modeLabel)
            }

            if (isYearMode) {
                listOfYears.chunked(4).forEach { chunkedYears ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        chunkedYears.forEach { year ->

                            BaseDatePickerButton(
                                modifier = Modifier.minimumInteractiveComponentSize(),
                                onClick = onYearSelection,
                                isSelected = year == localDate.year,
                            ) {
                                TextField(text = year.toString())
                            }
                        }
                    }
                }
            } else {
                listOfMonthsByDisplayName.chunked(4).forEach { chunkedMonths ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        chunkedMonths.forEach { month ->

                            BaseDatePickerButton(
                                modifier =
                                Modifier,
                                onClick = onMonthSelection,
                            ) {
                                TextField(text = month)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "BaseDateYearPicker")
@Composable
private fun PreviewBaseDateYearPicker() {
    BaseDateYearPicker()
}
