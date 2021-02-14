package com.zhukovartemvl.jetnotes.main_screen.di

import com.zhukovartemvl.jetnotes.main_screen.screen.main.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainScreenModule = module {

    viewModel { MainScreenViewModel(get()) }

}
