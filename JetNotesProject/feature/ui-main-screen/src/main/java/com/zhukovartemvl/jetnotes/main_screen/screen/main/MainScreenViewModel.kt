package com.zhukovartemvl.jetnotes.main_screen.screen.main

import androidx.lifecycle.viewModelScope
import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.common.repository.NoteRepository
import com.zhukovartemvl.jetnotes.common_ui.mvi_base.BaseViewModel
import kotlinx.coroutines.launch
import com.zhukovartemvl.jetnotes.main_screen.screen.main.MainScreenContract.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect


class MainScreenViewModel(private val notesRepository: NoteRepository) :
    BaseViewModel<Event, State, Action>() {

    init {
        if (currentState.screenState == ScreenState.Loading) {
            loadNotes()
        }
    }

    private fun loadNotes(searchFilter: String? = null) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.getAllNotesAsFlow().collect { notesList ->
            if (searchFilter == null) {
                setState { copy(screenState = ScreenState.DefaultList, notesList = notesList) }
            } else {
                setState {
                    copy(
                        screenState = ScreenState.Search(searchFilter),
                        notesList = notesList.filterByNameAndContent(searchFilter)
                    )
                }
            }
        }
    }

    private fun List<Note>.filterByNameAndContent(searchFilter: String) = this.filter { note ->
        note.title.contains(searchFilter) || note.content.contains(searchFilter)
    }

    override fun createInitialState(): State {
        return State(
            screenState = ScreenState.Loading,
            notesList = listOf(),
            dialogState = DialogState.None
        )
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnSelectionEnable -> {
                currentState.notesList.firstOrNull { it.id == event.firstSelectedItemId }
                    ?.let { note -> note.isSelected = true }
                val selectedItemsCount = currentState.notesList.count { it.isSelected }
                setState { copy(screenState = ScreenState.Selection(selectedItemsCount)) }
            }
            Event.OnSelectAllNotes -> {
                val isAllSelected = currentState.notesList.all { it.isSelected }
                currentState.notesList.forEach { it.isSelected = !isAllSelected }
                val selectedItemsCount = if (isAllSelected) 0 else currentState.notesList.size
                setState { copy(screenState = ScreenState.Selection(selectedItemsCount)) }
            }
            is Event.OnChangeNoteSelection -> {
                currentState.notesList.firstOrNull { it.id == event.noteId }?.let { note ->
                    note.isSelected = !note.isSelected
                }
                val selectedItemsCount = currentState.notesList.count { it.isSelected }
                setState { copy(screenState = ScreenState.Selection(selectedItemsCount)) }
            }
            Event.OnSelectionCancel -> {
                currentState.notesList.forEach { it.isSelected = false }
                setState { copy(screenState = ScreenState.DefaultList) }
            }

            Event.OnDeleteSelectedNotes -> {
                val notesToRemove = currentState.notesList.filter { it.isSelected }
                setState { copy(dialogState = DialogState.DeleteNotes(notesToRemove.size)) }
            }
            Event.OnDeleteSelectedNotesDialogAccepted -> viewModelScope.launch(Dispatchers.IO) {
                val notesToRemove = currentState.notesList.filter { it.isSelected }
                notesRepository.deleteNotes(notesToRemove)
                setState { copy(dialogState = DialogState.None) }
            }

            Event.OnSearchEnable -> {
                setState { copy(screenState = ScreenState.Search("")) }
            }
            is Event.OnSearchChanged -> {
                loadNotes(event.searchFilter)
                setState { copy(screenState = ScreenState.Search(event.searchFilter)) }
            }
            Event.OnSearchCancel -> {
                loadNotes()
            }
            Event.OnSearchClear -> {
                loadNotes("")
            }

            Event.OnDialogDismiss -> {
                setState { copy(dialogState = DialogState.None) }
            }
            is Event.OnDeleteEmptyNote -> viewModelScope.launch(Dispatchers.IO) {
                notesRepository.deleteNoteById(event.noteId)
            }
        }
    }
}
