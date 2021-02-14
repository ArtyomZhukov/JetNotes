package com.zhukovartemvl.jetnotes.main_screen.components.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.zhukovartemvl.jetnotes.resources.R


@Composable
fun CreateNoteDialog(onCreate: (noteName: String) -> Unit, onDismiss: () -> Unit) {
    var noteName by remember { mutableStateOf(TextFieldValue("")) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(id = R.string.new_note)) },
        text = {
            TextField(
                value = noteName,
                onValueChange = { newValue -> noteName = newValue },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                label = { Text(stringResource(id = R.string.title)) }
            )
        },
        confirmButton = {
            TextButton(onClick = { onCreate(noteName.text) }) {
                Text(text = stringResource(id = R.string.create))
            }
        }
    )
}
