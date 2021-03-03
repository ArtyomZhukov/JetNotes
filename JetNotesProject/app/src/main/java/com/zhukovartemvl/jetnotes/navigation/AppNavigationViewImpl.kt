package com.zhukovartemvl.jetnotes.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.zhukovartemvl.jetnotes.common_ui.ui.JetNotesTheme
import com.zhukovartemvl.jetnotes.common_ui.navigation.AppNavigationView
import com.zhukovartemvl.jetnotes.common_ui.navigation.AppNavigationParams
import com.zhukovartemvl.jetnotes.main_screen.AboutScreen
import com.zhukovartemvl.jetnotes.main_screen.screen.main.MainScreen
import com.zhukovartemvl.jetnotes.note_editor.NoteEditorScreen


class AppNavigationViewImpl : AppNavigationView {

    override fun content(context: Context) = ComposeView(context).apply {
        setContent { NavigationHost() }
    }

    @Composable
    private fun NavigationHost() = JetNotesTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = AppNavigationParams.Screen.Main) {
            composable(AppNavigationParams.Screen.Main) {
                MainScreen(navController)
            }
            composable(AppNavigationParams.Screen.About) {
                AboutScreen(navController)
            }
            composable(AppNavigationParams.Destination.NoteEditor,
                arguments = listOf(
                    navArgument(AppNavigationParams.Argument.NoteId) { type = NavType.IntType }
                )
            ) { backStack ->
                val noteId = backStack.arguments?.getInt(AppNavigationParams.Argument.NoteId) ?: -1
                NoteEditorScreen(navController, noteId)
            }
        }
    }

}


