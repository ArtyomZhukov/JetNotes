package com.zhukovartemvl.jetnotes.note_editor.app_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable


@Composable
internal fun DoneAppBarAction(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(imageVector = Icons.Default.Done, contentDescription = null)
    }
}
