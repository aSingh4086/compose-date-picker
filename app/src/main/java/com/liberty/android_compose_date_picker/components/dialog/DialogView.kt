package com.liberty.android_compose_date_picker.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.liberty.android_compose_date_picker.components.Button
import com.liberty.android_compose_date_picker.components.TextField


@Composable
fun BaseDialogView(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    headerContent: @Composable () -> Unit = {},
    footerContent: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit,
) {
    Box(
        modifier =
        Modifier,
    ) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier =
                    modifier
                        .heightIn(min = 200.dp, max = 500.dp)
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(modifier = Modifier.weight(0.2f)) {
                    headerContent()
                }
                Box(
                    Modifier
                        .weight(0.6f)
                        .background(Color.White, RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    content()
                }

                Box(
                    modifier =
                        Modifier
                            .weight(0.2f)
                            .fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd,
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        footerContent()
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewDialogView() {
    Box(
        modifier =
        Modifier,
    ) {
        BaseDialogView(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = {},
            footerContent = {
                Button(onClick = {}) {
                    TextField("Test")
                }
                Button(onClick = {}) {
                    TextField("Test")
                }
            },
        ) {}
    }
}
