package com.zhukovartemvl.jetnotes.note_editor.app_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.zhukovartemvl.jetnotes.note_editor.R


@Composable
internal fun HistoryAppBarCommands(
    isUndoEnabled: Boolean,
    isRedoEnabled: Boolean,
    onUndoClick: () -> Unit,
    onRedoClick: () -> Unit
) {
    IconButton(onClick = { onUndoClick() }, enabled = isUndoEnabled) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_undo),
            contentDescription = null
        )
    }
    IconButton(onClick = { onRedoClick() }, enabled = isRedoEnabled) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_redo),
            contentDescription = null
        )
    }
}
