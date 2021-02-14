package com.zhukovartemvl.jetnotes.di

import com.zhukovartemvl.jetnotes.data.di.dataModule
import com.zhukovartemvl.jetnotes.main_screen.di.mainScreenModule


val moduleList = listOf(
    appModule,
    dataModule,
    mainScreenModule
)
