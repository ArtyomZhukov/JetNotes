package com.zhukovartemvl.jetnotes.main_screen.components.app_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.navigate
import com.zhukovartemvl.jetnotes.common_ui.navigation.AppNavigationParams
import com.zhukovartemvl.jetnotes.main_screen.R


@Composable
internal fun DefaultAppBar(onSearchButtonClick: () -> Unit, onAboutButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = { onSearchButtonClick() }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
            IconButton(onClick = { onAboutButtonClick() }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = null)
            }
        }
    )
}
