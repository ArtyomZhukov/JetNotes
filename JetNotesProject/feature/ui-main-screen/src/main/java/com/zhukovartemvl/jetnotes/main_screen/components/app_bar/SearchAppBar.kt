package com.zhukovartemvl.jetnotes.main_screen.components.app_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zhukovartemvl.jetnotes.resources.R


@Composable
fun SearchAppBar(
    searchFilter: String,
    onSearchFilterChanged: (text: String) -> Unit = {},
    onCancelClick: () -> Unit,
    onClearClick: () -> Unit
) {
    TopAppBar(
        title = { TextInput(text = searchFilter, onTextChanged = onSearchFilterChanged) },
        navigationIcon = {
            IconButton(onClick = { onCancelClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            if (searchFilter.isNotEmpty()) {
                IconButton(onClick = { onClearClick() }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun TextInput(text: String, onTextChanged: (text: String) -> Unit) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_notes),
                    color = Color.White.copy(alpha = 0.75f)
                )
            },
            modifier = Modifier.fillMaxWidth().background(color = Color.Transparent),
            textStyle = MaterialTheme.typography.subtitle1,
            shape = RoundedCornerShape(4.dp),
        )
    }
}
