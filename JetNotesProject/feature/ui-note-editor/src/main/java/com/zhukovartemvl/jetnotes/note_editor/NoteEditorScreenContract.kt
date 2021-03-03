package com.zhukovartemvl.jetnotes.note_editor

import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.UiAction
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.UiEvent
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.UiState


class NoteEditorScreenContract {

    sealed class Event : UiEvent {
        class OnTitleChanged(val title: String) : Event()
        class OnContentChanged(val content: String) : Event()


        object OnCommandUndo : Event()
        object OnCommandRedo: Event()
        object OnDoneButtonClicked : Event()
    }

    data class State(
        val screenState: ScreenState,
        val note: Note
    ) : UiState

    sealed class ScreenState {
        object Loading : ScreenState()
        object Default : ScreenState()
        object TitleChanging : ScreenState()
        class ContentChanging(val undoEnable: Boolean, val redoEnable: Boolean) : ScreenState()
    }

    sealed class Action : UiAction
}
