package com.zhukovartemvl.jetnotes.note_editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zhukovartemvl.jetnotes.common_ui.getViewModel
import com.zhukovartemvl.jetnotes.note_editor.NoteEditorScreenContract.*
import com.zhukovartemvl.jetnotes.note_editor.app_bar.NoteEditorAppBar
import com.zhukovartemvl.jetnotes.note_editor.app_bar.DoneAppBarAction
import com.zhukovartemvl.jetnotes.note_editor.app_bar.HistoryAppBarCommands
import com.zhukovartemvl.jetnotes.resources.R


@Composable
fun NoteEditorScreen(
    navController: NavController,
    noteId: Int = -1,
    viewModel: NoteEditorScreenViewModel = getViewModel()
) {
    viewModel.init(noteId)
    val state by viewModel.uiState.collectAsState()
    Scaffold(topBar = {
        NoteEditorAppBar(onNavBackClick = { navController.popBackStack() },
            actions = {
                when (val screenState = state.screenState) {
                    is ScreenState.ContentChanging -> {
                        HistoryAppBarCommands(
                            isUndoEnabled = screenState.undoEnable,
                            isRedoEnabled = screenState.redoEnable,
                            onUndoClick = { viewModel.setEvent(Event.OnCommandUndo) },
                            onRedoClick = { viewModel.setEvent(Event.OnCommandRedo) })
                        DoneAppBarAction(
                            onClick = { viewModel.setEvent(Event.OnDoneButtonClicked) }
                        )
                    }
                    ScreenState.TitleChanging -> DoneAppBarAction(
                        onClick = { viewModel.setEvent(Event.OnDoneButtonClicked) }
                    )
                    else -> Unit
                }
            }
        )
    }) {
        Column {
            TitleEditText(
                title = state.note.title,
                onTitleChanged = { title -> viewModel.setEvent(Event.OnTitleChanged(title)) }
            )
            ContentEditText(
                content = state.note.content,
                onContentChanged = { content -> viewModel.setEvent(Event.OnContentChanged(content)) }
            )
        }
    }
}

@Composable
private fun TitleEditText(title: String, onTitleChanged: (title: String) -> Unit) {

    TextField(
        value = title,
        onValueChange = { newValue -> onTitleChanged(newValue) },
//        onTextInputStarted = { onTitleChanged(title) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.title),
                color = Color.White.copy(alpha = 0.75f)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        textStyle = MaterialTheme.typography.subtitle1,
        shape = RoundedCornerShape(4.dp)
    )
}

@Composable
private fun ContentEditText(content: String, onContentChanged: (content: String) -> Unit) {
    TextField(
        value = content,
        onValueChange = { newValue -> onContentChanged(newValue) },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        textStyle = MaterialTheme.typography.subtitle1,
        shape = RoundedCornerShape(4.dp),
    )
}
