package com.zhukovartemvl.jetnotes.di

import com.zhukovartemvl.jetnotes.common_ui.navigation.AppNavigationView
import com.zhukovartemvl.jetnotes.navigation.AppNavigationViewImpl
import org.koin.dsl.module


val appModule = module {

    single { AppNavigationViewImpl() as AppNavigationView }

}

