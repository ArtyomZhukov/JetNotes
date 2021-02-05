package com.zhukovartemvl.jetnotes.data.di

import android.content.Context
import androidx.room.Room
import com.zhukovartemvl.jetnotes.common.repository.NoteRepository
import com.zhukovartemvl.jetnotes.common.R
import com.zhukovartemvl.jetnotes.data.db.AppDatabase
import com.zhukovartemvl.jetnotes.data.repository.NoteRepositoryImpl
import org.koin.dsl.module


val dataModule = module {

    single { getDatabase(get()) }
    single { NoteRepositoryImpl(get()) as NoteRepository }

}

private fun getDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, context.getString(R.string.db_name))
        .fallbackToDestructiveMigration()
        .build()

