package com.zhukovartemvl.jetnotes.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.zhukovartemvl.jetnotes.common_ui.navigation.AppNavigationParams
import androidx.navigation.compose.navigate


@Composable
fun MainScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(R.string.app_name)) },
            actions = {
                IconButton(onClick = { navController.navigate(AppNavigationParams.Screen.About) }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null)
                }
            }
        )
    }) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TestNavButton(0, navController)
            TestNavButton(1, navController)
            TestNavButton(2, navController)
        }
    }
}

@Composable
fun TestNavButton(id: Int, navController: NavController) {
    Button(onClick = { navController.navigate(AppNavigationParams.Screen.NoteEditor(id)) }) {
        Text(text = "Button $id")
    }
}

