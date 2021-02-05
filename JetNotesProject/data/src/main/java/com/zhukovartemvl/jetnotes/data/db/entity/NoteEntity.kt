package com.zhukovartemvl.jetnotes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
data class NoteEntity(
    var title: String,
    var content: String,
    var createdTime: Long,
    var changedTime: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
