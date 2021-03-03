package com.zhukovartemvl.jetnotes.main_screen.screen.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.zhukovartemvl.jetnotes.common_ui.navigation.AppNavigationParams
import androidx.navigation.compose.navigate
import com.zhukovartemvl.jetnotes.common_ui.components.LazyGrid
import com.zhukovartemvl.jetnotes.common_ui.getViewModel
import com.zhukovartemvl.jetnotes.common_ui.navigation.BackButtonHandler
import com.zhukovartemvl.jetnotes.main_screen.R
import com.zhukovartemvl.jetnotes.main_screen.components.AddNoteFloatingButton
import com.zhukovartemvl.jetnotes.main_screen.components.NoteItem
import com.zhukovartemvl.jetnotes.main_screen.components.app_bar.DefaultAppBar
import com.zhukovartemvl.jetnotes.main_screen.components.app_bar.SearchAppBar
import com.zhukovartemvl.jetnotes.main_screen.components.app_bar.SelectionModeAppBar
import com.zhukovartemvl.jetnotes.main_screen.components.dialogs.DeleteNotesDialog
import com.zhukovartemvl.jetnotes.main_screen.screen.main.MainScreenContract.*


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = getViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            MainScreenAppBar(state.screenState, navController) { e -> viewModel.setEvent(e) }
        },
        floatingActionButton = {
            if (state.screenState is ScreenState.DefaultList) {
                AddNoteFloatingButton(onClick = { navController.navigate(AppNavigationParams.Screen.NoteEditor()) })
            }
        },
        bottomBar = {
            if (state.screenState is ScreenState.Selection) {
                BottomBar(onDeleteItemsClick = { viewModel.setEvent(Event.OnDeleteSelectedNotes) })
            }
        }) {
        MainScreenContent(state, navController) { e -> viewModel.setEvent(e) }
    }

    val (enableBackHandler: Boolean, onBackPressed: () -> Unit) = when (state.screenState) {
        is ScreenState.Search -> true to { viewModel.setEvent(Event.OnSearchCancel) }
        is ScreenState.Selection -> true to { viewModel.setEvent(Event.OnSelectionCancel) }
        else -> false to {}
    }
    BackButtonHandler(enabled = enableBackHandler, onBackPressed = onBackPressed)
}

@Composable
private fun MainScreenAppBar(
    screenState: ScreenState,
    navController: NavController,
    setEvent: (event: Event) -> Unit
) = when (screenState) {
    is ScreenState.Search -> SearchAppBar(
        searchFilter = screenState.searchFilter,
        onSearchFilterChanged = { text -> setEvent(Event.OnSearchChanged(text)) },
        onCancelClick = { setEvent(Event.OnSearchCancel) },
        onClearClick = { setEvent(Event.OnSearchClear) })
    is ScreenState.Selection -> SelectionModeAppBar(
        selectedItemsCount = screenState.selectedItemsCount,
        onCancelClick = { setEvent(Event.OnSelectionCancel) },
        onSelectAllClick = { setEvent(Event.OnSelectAllNotes) })
    else -> DefaultAppBar(
        onSearchButtonClick = { setEvent(Event.OnSearchEnable) },
        onAboutButtonClick = { navController.navigate(AppNavigationParams.Screen.About) })
}

@Composable
private fun MainScreenContent(
    state: MainScreenContract.State,
    navController: NavController,
    setEvent: (event: Event) -> Unit
) {
    if (state.screenState is ScreenState.Loading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        val orientation = LocalConfiguration.current.orientation
        val rowsCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
        val isSelectionMode = state.screenState is ScreenState.Selection

        LazyGrid(items = state.notesList, rows = rowsCount) { note, index ->
            NoteItem(
                note = note,
                isSelectionMode = isSelectionMode,
                onItemClick = { noteId ->
                    if (isSelectionMode) {
                        setEvent(Event.OnChangeNoteSelection(noteId))
                    } else {
                        navController.navigate(AppNavigationParams.Screen.NoteEditor(noteId))
                    }
                },
                onLongItemClick = { noteId ->
                    if (!isSelectionMode) {
                        setEvent(Event.OnSelectionEnable(noteId))
                    }
                },
                removeEmptyNote = { noteId -> setEvent(Event.OnDeleteEmptyNote(noteId)) }
            )
        }

    }
    Dialogs(dialogState = state.dialogState, setEvent = setEvent)
}

@Composable
private fun BottomBar(onDeleteItemsClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ) {
        IconButton(onClick = { onDeleteItemsClick() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}

@Composable
private fun Dialogs(
    dialogState: DialogState,
    setEvent: (event: Event) -> Unit
) {
    when (dialogState) {
        is DialogState.DeleteNotes -> DeleteNotesDialog(
            title = stringResource(id = R.string.delete_notes_dialog_title),
            subtitle = dialogState.selectedNotesCount.toString(),
            onAccept = { setEvent(Event.OnDeleteSelectedNotesDialogAccepted) },
            onDismiss = { setEvent(Event.OnDialogDismiss) }
        )
        DialogState.None -> Unit
    }
}
