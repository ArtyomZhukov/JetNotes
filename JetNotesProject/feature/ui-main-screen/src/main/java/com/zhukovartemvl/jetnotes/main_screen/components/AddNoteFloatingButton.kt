package com.zhukovartemvl.jetnotes.main_screen.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable


@Composable
internal fun AddNoteFloatingButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}
