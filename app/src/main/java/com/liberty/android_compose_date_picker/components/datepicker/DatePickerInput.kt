package com.liberty.android_compose_date_picker.components.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liberty.android_compose_date_picker.R
import com.liberty.android_compose_date_picker.components.Button
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.components.baseInput.BaseInput
import com.liberty.android_compose_date_picker.components.baseInput.BaseInputErrorConfig
import com.liberty.android_compose_date_picker.components.baseInput.BaseInputType
import com.liberty.android_compose_date_picker.components.dialog.BaseDialogView
import com.liberty.android_compose_date_picker.components.iconButton.IconButton
import com.liberty.android_compose_date_picker.components.iconButton.IconButtonImage
import com.liberty.android_compose_date_picker.utils.DateUtils
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun DatePickerDialog(
    onDateChange: (selectedData: String) -> Unit,
    actionButtons: @Composable RowScope.() -> Unit = {},
    onSave: (selectedData: String) -> Unit = {},
    onClose: () -> Unit = {},
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var currentYearMonth by remember { mutableStateOf((YearMonth.now())) }
    BaseDialogView(
        modifier =
        Modifier,
        onDismissRequest = onClose,
        headerContent = {
            Column(
                modifier =
                    Modifier.padding(
                        paddingValues =
                            PaddingValues(
                                start = 20.dp,
                                end = 20.dp,
                                top = 10.dp,
                            ),
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                TextField(
                    text = selectedDate.year.toString(),
                    style =
                        TextStyle(
                            color = Color.Gray,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                )
                TextField(
                    text = DateUtils.formatDateToShortStyle(selectedDate),
                    style =
                        TextStyle(
                            color = Color.Gray,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                )
            }
        },
        footerContent = {
            DatePickerFooter(
                content = {
                    actionButtons()
                    Button(
                        content = {
                            TextField(stringResource(R.string.cancel))
                        },
                        onClick = onClose,
                    )
                    Button(
                        content = {
                            TextField(stringResource(R.string.save))
                        },
                        onClick = {
                            onSave(selectedDate.toString())
                            onClose()
                        },
                    )
                },
            )
        },
        content = {
            BaseDatePickerWithAnimation(
                onPreviousMonth = {
                    currentYearMonth = currentYearMonth.minusMonths(1)
                },
                onNextMonth = {
                    currentYearMonth = currentYearMonth.plusMonths(1)
                },
                onDateChange = {
                    selectedDate = it
                    onDateChange(it.toString())
                },
                onClose = {},
                isDisplayFooter = false,
                customFooterContent = {},
            )
        },
    )
}

@Composable
fun DatePickerFooter(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}

@Composable
fun DatePickerInput(
    label: String = "Date",
    value: String? = null,
    onSave: (selectedData: String) -> Unit = {},
    isRequired: Boolean = false,
    errors: List<String> = emptyList(),
    onDateChange: (selectedData: String) -> Unit = {},
) {
    var isDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }

    fun handleDateChange(selectedDate: String) {
        onDateChange(selectedDate)
        // isDialogOpen = false
    }

    BaseInput(
        inputType = BaseInputType.Date,
        value = value,
        onValueChange = onDateChange,
        isRequired = isRequired,
        label = label,
        errorConfig =
            BaseInputErrorConfig(
                errors = errors,
                isErrorVisible = errors.isNotEmpty(),
            ),
        trailingIcon = {
            IconButton(content = {
                IconButtonImage(imageId = R.drawable.icon_calendar)
            }, onClick = {
                isDialogOpen = true
            })
        },
    )

    if (isDialogOpen) {
        DatePickerDialog(::handleDateChange, onClose = {
            isDialogOpen = false
        }, onSave = {
            onSave(it)
        })
    }
}

@Composable
@Preview
fun DatePickerSample() {
    DatePickerInput(value = "2024-12-12") { }
}
