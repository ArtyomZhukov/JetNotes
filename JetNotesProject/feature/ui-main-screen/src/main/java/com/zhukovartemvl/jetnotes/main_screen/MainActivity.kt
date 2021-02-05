package com.zhukovartemvl.jetnotes.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhukovartemvl.jetnotes.common_ui.navigation.AppNavigationView
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val appView: AppNavigationView by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(appView.content(this))
    }
}

