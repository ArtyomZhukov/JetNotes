package com.zhukovartemvl.jetnotes.note_editor

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun NoteEditorScreen(navController: NavController, noteId: Int) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Note title") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
    }) {
        Text(text = noteId.toString())
    }

}

