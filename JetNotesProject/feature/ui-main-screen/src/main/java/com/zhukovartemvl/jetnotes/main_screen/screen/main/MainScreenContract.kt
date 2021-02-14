package com.zhukovartemvl.jetnotes.main_screen.screen.main

import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.UiAction
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.UiEvent
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.UiState


class MainScreenContract {
    sealed class Event : UiEvent {
        object OnAddNoteFabClicked : Event()
        class OnAddNoteDialogChangedTitle(val newTitle: String): Event()
        class OnAddNoteDialogAccepted(val title: String) : Event()

        object OnSearchEnable : Event()
        class OnSearchChanged(val searchFilter: String) : Event()
        object OnSearchClear : Event()
        object OnSearchCancel : Event()

        class OnSelectionEnable(val firstSelectedItemId: Int) : Event()
        class OnChangeNoteSelection(val noteId: Int) : Event()
        object OnSelectAllNotes : Event()
        object OnSelectionCancel : Event()

        object OnDeleteSelectedNotes : Event()
        object OnDeleteSelectedNotesDialogAccepted : Event()

        object OnDialogDismiss : Event()
    }

    data class State(
        val screenState: ScreenState,
        val notesList: List<Note>,
        val dialogState: DialogState
    ) : UiState

    sealed class ScreenState {
        object Loading : ScreenState()
        object DefaultList : ScreenState()
        class Search(val searchFilter: String) : ScreenState()
        class Selection(val selectedItemsCount: Int) : ScreenState()
    }

    sealed class DialogState {
        object None : DialogState()
        class CreateNote(val title: String) : DialogState()
        class DeleteNotes(val selectedNotesCount: Int) : DialogState()
    }

    sealed class Action : UiAction {
        object NewNoteAddedMessage : Action()
        class NotesDeletedMessage(val deletedMotesCount: Int) : Action()
    }
}
