package com.zhukovartemvl.jetnotes.main_screen.components.app_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable


@Composable
fun SelectionModeAppBar(
    selectedItemsCount: Int,
    onCancelClick: () -> Unit,
    onSelectAllClick: () -> Unit
) {
    val title = if (selectedItemsCount == 0) {
        "Select items"
    } else {
        "$selectedItemsCount item selected"
    }
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onCancelClick() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onSelectAllClick() }) {
                Icon(imageVector = Icons.Default.List, contentDescription = null)
            }
        }
    )
}
