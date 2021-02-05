package com.zhukovartemvl.jetnotes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhukovartemvl.jetnotes.data.db.dao.NoteDao
import com.zhukovartemvl.jetnotes.data.db.entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}

