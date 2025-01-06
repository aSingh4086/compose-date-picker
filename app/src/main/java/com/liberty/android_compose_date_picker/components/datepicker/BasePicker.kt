package com.liberty.android_compose_date_picker.components.datepicker

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.liberty.android_compose_date_picker.components.datepicker.components.BaseDatePickerBody
import com.liberty.android_compose_date_picker.components.datepicker.components.BaseDatePickerFooter
import com.liberty.android_compose_date_picker.components.datepicker.components.BaseDatePickerHeader
import com.liberty.android_compose_date_picker.components.datepicker.components.yearPicker.BaseYearPickerSpinner
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth


@Composable
fun BaseDatePickerWithAnimation(
    modifier: Modifier = Modifier,
    onPreviousMonth: () -> Unit = {},
    onNextMonth: () -> Unit = {},
    onDateChange: (selectedData: LocalDate) -> Unit = {},
    onClose: () -> Unit = {},
    isDisplayFooter: Boolean = false,
    customFooterContent: () -> Unit = {},
    dayCellContent: (
        @Composable (
        RowScope.(day: LocalDate) -> Unit
    ))? = null,
    animatedHeight: Dp = 0.dp,
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var currentYearMonth by remember { mutableStateOf((YearMonth.now())) }
    var isYearSelectionEnabled by remember { mutableStateOf(true) }
    var datePickerBodyOffset by remember { mutableStateOf(Offset.Zero) }
    var targetDirection by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        BaseDatePickerHeader(
            currentYearMonth = currentYearMonth,
            onPreviousMonth = {
                currentYearMonth = currentYearMonth.minusMonths(1)
                onPreviousMonth()
                targetDirection = -1
            },
            onNextMonth = {
                currentYearMonth = currentYearMonth.plusMonths(1)
                onNextMonth()
                targetDirection = 1
            },
            onClickYear = {
                isYearSelectionEnabled = !isYearSelectionEnabled
            },
        )

        Box(
            modifier =
                Modifier.fillMaxWidth().padding(10.dp),
            contentAlignment = Alignment.Center,
        ) {
            AnimatedContent(
                targetState = currentYearMonth,
                transitionSpec = {
                    if (targetDirection == 1) {
                        (
                            slideInHorizontally(
                                animationSpec =
                                    tween(
                                        200,
                                        easing = LinearEasing,
                                    ),
                                initialOffsetX = { -it },
                            )
                        ).togetherWith(
                            slideOutHorizontally(
                                animationSpec = tween(200, easing = LinearEasing),
                                targetOffsetX = { +it },
                            ),
                        )
                    } else {
                        (
                            slideInHorizontally(
                                animationSpec =
                                    tween(
                                        200,
                                        easing = LinearEasing,
                                    ),
                                initialOffsetX = { it },
                            )
                        ).togetherWith(
                            slideOutHorizontally(
                                animationSpec = tween(200, easing = LinearEasing),
                                targetOffsetX = { -it },
                            ),
                        )
                    }
                },
                contentAlignment = Alignment.Center,
                label = "",
            ) { targetedYearMonth ->

                Log.e("targetYearMonth", datePickerBodyOffset.toString())
                val density = LocalDensity.current

                if (isYearSelectionEnabled) {
                    BaseYearPickerSpinner(
                        onClick = {},
                        startYear = Year.of(1990),
                    )
                } else {
                    BaseDatePickerBody(
                        yearMonth= targetedYearMonth,
                        selectedDate = selectedDate,
                        dayCellContent = dayCellContent,
                        onMonthChange = {
                            currentYearMonth = it
                        },
                        onDateSelected = {
                            selectedDate = it
                            onDateChange(it)
                        },
                    )
                }
            }
        }

        if (isDisplayFooter) {
            customFooterContent()
            BaseDatePickerFooter(onClose = onClose)
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
        customFooterContent = {},
    )
}
