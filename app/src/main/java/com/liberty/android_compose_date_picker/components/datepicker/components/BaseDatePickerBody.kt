package com.liberty.android_compose_date_picker.components.datepicker.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.components.datepicker.BaseDatePickerWithAnimation
import com.liberty.android_compose_date_picker.designTokens.DesignToken
import com.liberty.android_compose_date_picker.utils.DatePickerUtils.getDaysInMonth
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun BaseDatePickerBody(
    yearMonth: YearMonth,
    selectedDate: LocalDate,
    dateContainerOffset: Float = 0f,
    onMonthChange: (YearMonth) -> Unit = {},
    dayCellContent: (@Composable (RowScope.(LocalDate) -> Unit))? = null,
    onDateSelected: (LocalDate) -> Unit,
) {
    var selectedWeek by remember { mutableIntStateOf(0) }

    Column(
        modifier =
            Modifier.background(Color.White),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DayOfWeek.entries.forEach {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    TextField(
                        text =
                            it.getDisplayName(
                                TextStyle.SHORT,
                                Locale.getDefault(),
                            ),
                    )
                }
            }
        }

        Box(
            Modifier.weight(1f),
        ) {
            val newDaysInMonth = getDaysInMonth(yearMonth)
            Column(modifier = Modifier) {
                val weeks = newDaysInMonth.chunked(7)
                weeks.forEachIndexed { weekIndex, week ->
                    Row(
                        modifier =
                            Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val weekNotNull = week.filterNotNull()
                        Log.e("weekNotNull", weekNotNull.size.toString())
                        for (day in week) {
                            if (day == null) {
                                Spacer(modifier = Modifier.weight(1f))
                            } else {
                                val isSelected = day == selectedDate
                                val isSameMonth = day.month == yearMonth.month

                                val textColor =
                                    if (isSelected) {
                                        DesignToken.ColorToken.color_azalea
                                    } else if (isSameMonth) {
                                        DesignToken.ColorToken.dark
                                    } else {
                                        DesignToken.BrandColorToken.brand_color_alto
                                    }
                                if (isSelected) {
                                    selectedWeek = weekIndex
                                }
                                val backgroundColor by animateColorAsState(
                                    targetValue =
                                        if (isSelected) {
                                            DesignToken.ColorToken.color_cornflower_blue
                                        } else if (!isSameMonth) {
                                            DesignToken.BrandColorToken.brand_color_gallery
                                        } else {
                                            Color.Transparent
                                        },
                                    label = "",
                                )

                                if (dayCellContent != null) {
                                    dayCellContent(day)
                                } else {
                                    Box(
                                        modifier =
                                            Modifier

                                                .weight(1f)
                                                .fillMaxSize()
                                                .background(backgroundColor)
                                                //   .padding(5.dp)
                                                .pointerInput(Unit) {
                                                    detectTapGestures {
                                                        onDateSelected(day)
                                                    }
                                                },
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        TextField(
                                            modifier = Modifier,
                                            text = day.dayOfMonth.toString() ?: "",
                                            color = textColor,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun BaseDatePickerReview() {
    BaseDatePickerWithAnimation(
        onPreviousMonth = {},
        onNextMonth = {},
        onDateChange = {},
        onClose = {},
        isDisplayFooter = false,
        customFooterContent = {},
    )
}
