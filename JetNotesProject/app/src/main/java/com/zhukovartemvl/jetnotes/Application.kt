package com.zhukovartemvl.jetnotes

import androidx.multidex.MultiDexApplication
import com.zhukovartemvl.jetnotes.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(moduleList)
        }
    }

}

