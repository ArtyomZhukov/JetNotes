package com.zhukovartemvl.jetnotes.main_screen.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.common.utils.toDateString
import com.zhukovartemvl.jetnotes.resources.R


@Composable
fun NoteItem(
    note: Note,
    isSelectionMode: Boolean,
    onItemClick: (noteId: Int) -> Unit,
    onLongItemClick: (noteId: Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable(
                onClick = { onItemClick(note.id) },
                onLongClick = { onLongItemClick(note.id) }
            )
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = note.title)
            Spacer(modifier = Modifier.preferredHeight(6.dp))
            Text(
                text = note.content,
                maxLines = 4,
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.preferredHeight(6.dp))
            Text(text = note.changedTime.toDateString())
        }
        if (isSelectionMode) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                SelectionMarker(isSelected = note.isSelected)
            }
        }

    }
}

@Composable
private fun SelectionMarker(isSelected: Boolean) {
    val icon = if (isSelected) Icons.Default.CheckCircle else vectorResource(R.drawable.ic_circle)
    val tint = if (isSelected) Color.Blue.copy(alpha = 0.75f) else Color.LightGray
    Icon(
        imageVector = icon,
        tint = tint,
        modifier = Modifier.preferredSize(24.dp),
        contentDescription = null
    )
}

@Preview
@Composable
private fun NoteItemPreview() {
    NoteItem(
        note = Note(
            title = "Test title",
            content = "QEFAEfafasf\nasdasdasdasd\nasdasdasd\nasdasdasdasd\nsadasdsadsad\n",
            changedTime = 123123123123
        ),
        isSelectionMode = false,
        onItemClick = {},
        onLongItemClick = {}
    )
}

@Preview
@Composable
private fun NoteItemSelectedPreview() {
    NoteItem(
        note = Note(
            title = "Test title",
            content = "QEFAEfafasf\nasdasdasdasd\nasdasdasd\nasdasdasdasd\nsadasdsadsad\n",
            changedTime = 123123123123
        ),
        isSelectionMode = true,
        onItemClick = {},
        onLongItemClick = {}
    )
}
