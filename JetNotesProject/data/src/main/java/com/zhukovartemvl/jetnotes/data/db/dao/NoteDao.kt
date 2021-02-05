package com.zhukovartemvl.jetnotes.data.db.dao

import androidx.room.*
import com.zhukovartemvl.jetnotes.common.model.Note
import com.zhukovartemvl.jetnotes.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id LIMIT 1")
    fun getById(id: Int): NoteEntity

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note")
    fun getAllAsFlow(): Flow<List<Note>>

    @Query("SELECT COUNT(id) FROM note")
    fun getCount(): Int

}

