package com.zhukovartemvl.jetnotes.di

import com.zhukovartemvl.jetnotes.data.di.dataModule
import com.zhukovartemvl.jetnotes.main_screen.di.mainScreenModule
import com.zhukovartemvl.jetnotes.note_editor.di.noteEditorScreenModule


val moduleList = listOf(
    appModule,
    dataModule,
    mainScreenModule,
    noteEditorScreenModule
)
