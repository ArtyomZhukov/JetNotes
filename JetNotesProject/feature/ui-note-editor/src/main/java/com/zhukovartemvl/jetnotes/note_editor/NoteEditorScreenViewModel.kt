package com.zhukovartemvl.jetnotes.note_editor

import androidx.lifecycle.viewModelScope
import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.common.repository.NoteRepository
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.BaseViewModel
import com.zhukovartemvl.jetnotes.note_editor.NoteEditorScreenContract.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteEditorScreenViewModel(private val notesRepository: NoteRepository) :
    BaseViewModel<Event, State, Action>() {

    fun init(noteId: Int = -1) = viewModelScope.launch(Dispatchers.IO) {
        if (currentState.screenState is ScreenState.Loading) {
            val note = if (noteId != -1)
                notesRepository.getNoteById(noteId)
            else
                notesRepository.addNote("")
            setState { copy(screenState = ScreenState.Default, note = note) }
        }
    }

    override fun createInitialState(): State {
        return State(screenState = ScreenState.Loading, note = Note())
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnTitleChanged -> viewModelScope.launch(Dispatchers.IO) {
                notesRepository.editNoteTitle(currentState.note.id, event.title)
                setState {
                    copy(
                        screenState = ScreenState.TitleChanging,
                        note = currentState.note.copy(title = event.title)
                    )
                }
            }
            is Event.OnContentChanged -> viewModelScope.launch(Dispatchers.IO) {
                notesRepository.editNoteContent(currentState.note.id, event.content)
                setState {
                    copy(
                        screenState = ScreenState.ContentChanging(
                            undoEnable = true,
                            redoEnable = true
                        ),
                        note = currentState.note.copy(content = event.content)
                    )
                }
            }
            Event.OnCommandRedo -> {

            }
            Event.OnCommandUndo -> {

            }
            Event.OnDoneButtonClicked -> {
                setState { copy(screenState = ScreenState.Default) }
            }
        }
    }

}
