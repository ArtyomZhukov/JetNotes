package com.zhukovartemvl.jetnotes.common_ui.navigation

import android.content.Context
import androidx.compose.ui.platform.ComposeView


interface AppNavigationView {

    fun content(context: Context): ComposeView

}

