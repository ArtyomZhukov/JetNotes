package com.zhukovartemvl.jetnotes.main_screen.components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zhukovartemvl.jetnotes.main_screen.R


@Composable
internal fun DeleteNotesDialog(title: String, subtitle: String, onAccept: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = subtitle) },
        confirmButton = {
            TextButton(onClick = onAccept) {
                Text(text = stringResource(id = R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

