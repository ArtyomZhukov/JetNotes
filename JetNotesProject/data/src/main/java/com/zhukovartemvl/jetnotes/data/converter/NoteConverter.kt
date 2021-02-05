package com.zhukovartemvl.jetnotes.data.converter

import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.data.db.entity.NoteEntity


fun NoteEntity.toNote(): Note {
    return  Note(id, title, content, createdTime, changedTime)
}

fun Note.toNoteEntity(): NoteEntity {
    val noteEntity =
        NoteEntity(title, content, createdTime, changedTime)
    if (id >= 0)
        noteEntity.id = id
    return noteEntity
}

