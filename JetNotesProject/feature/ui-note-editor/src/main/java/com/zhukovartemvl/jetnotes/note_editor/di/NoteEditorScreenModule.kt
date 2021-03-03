package com.zhukovartemvl.jetnotes.note_editor.di

import com.zhukovartemvl.jetnotes.note_editor.NoteEditorScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val noteEditorScreenModule = module {

    viewModel { NoteEditorScreenViewModel(get()) }

}
