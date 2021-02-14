package com.zhukovartemvl.jetnotes.common.repository

import com.zhukovartemvl.jetnotes.common.model.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {

    fun addNote(note: Note)
    fun addNote(title: String)
    fun editNoteTitle(id: Int, title: String)
    fun editNoteContent(id: Int, content: String)
    fun deleteNoteById(id: Int)
    fun deleteNote(note: Note)
    fun deleteNotes(notes: List<Note>)
    fun getNoteById(id: Int): Note
    fun getAllNotesAsFlow(): Flow<List<Note>>
    fun getAllNotes(): List<Note>
    fun getNotesCount(): Int

}

