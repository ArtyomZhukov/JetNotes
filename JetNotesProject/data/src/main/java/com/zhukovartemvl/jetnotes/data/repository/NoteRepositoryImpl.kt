package com.zhukovartemvl.jetnotes.data.repository

import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.common.repository.NoteRepository
import com.zhukovartemvl.jetnotes.data.converter.toNote
import com.zhukovartemvl.jetnotes.data.converter.toNoteEntity
import com.zhukovartemvl.jetnotes.data.db.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class NoteRepositoryImpl(private val database: AppDatabase) : NoteRepository {

    override fun addNote(note: Note) {
        database.noteDao().insert(note.toNoteEntity())
    }

    override fun addNote(title: String) {
        val createdTime = System.currentTimeMillis()
        addNote(Note(title = title, createdTime = createdTime, changedTime = createdTime))
    }

    override fun editNoteTitle(id: Int, title: String) {
        val note = getNoteById(id).apply { this.title = title }
        updateNote(note)
    }

    override fun editNoteContent(id: Int, content: String) {
        val note = getNoteById(id).apply { this.content = content }
        updateNote(note)
    }

    private fun updateNote(note: Note) {
        note.changedTime = System.currentTimeMillis()
        database.noteDao().update(note.toNoteEntity())
    }

    override fun deleteNoteById(id: Int) {
        deleteNote(getNoteById(id))
    }

    override fun deleteNote(note: Note) {
        database.noteDao().delete(note.toNoteEntity())
    }

    override fun deleteNotes(notes: List<Note>) {
        database.noteDao().delete(notes.map { it.toNoteEntity() })
    }

    override fun getNoteById(id: Int): Note {
        return database.noteDao().getById(id).toNote()
    }

    override fun getAllNotesAsFlow(): Flow<List<Note>> {
        return database.noteDao().getAllAsFlow().map { list -> list.map { note -> note.toNote() } }
    }

    override fun getAllNotes(): List<Note> {
        return database.noteDao().getAll().map { note -> note.toNote() }
    }

    override fun getNotesCount(): Int {
        return database.noteDao().getCount()
    }

}
