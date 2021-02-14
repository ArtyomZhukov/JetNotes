package com.zhukovartemvl.jetnotes.main_screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "AboutScreen") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
    }) {
        Text(text = "AboutScreen")
    }
}

