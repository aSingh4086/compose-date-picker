package com.liberty.android_compose_date_picker.components.datepicker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liberty.android_compose_date_picker.components.BaseSurface
import com.liberty.android_compose_date_picker.components.TextField
import com.liberty.android_compose_date_picker.designTokens.DesignToken

@Composable
fun ListItemActions(content: LazyListScope.() -> Unit) {
    LazyColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        content()
    }
}

fun LazyListScope.ListActionItem(
    content: @Composable RowScope.() -> Unit,
    onClick: () -> Unit = {},
) {
    item {
        BaseSurface(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
            backgroundColor = Color.White,
            contentColor = DesignToken.ColorToken.dark,
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                content()
            }
        }
    }
}

@Composable
@Preview
fun ListItemActionsPreview() {
    ListItemActions {
        ListActionItem(onClick = {}, content = {
            TextField("test")
        })
        ListActionItem(onClick = {}, content = {
            TextField("test2")
        })
    }
}
