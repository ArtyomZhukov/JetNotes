package com.zhukovartemvl.jetnotes.note_editor.app_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable


@Composable
internal fun NoteEditorAppBar(onNavBackClick: () -> Unit, actions: @Composable () -> Unit = {}) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { onNavBackClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = { actions() }
    )
}
