package com.zhukovartemvl.jetnotes.main_screen.components.app_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zhukovartemvl.jetnotes.resources.R


@Composable
internal fun SearchAppBar(
    searchFilter: String,
    onSearchFilterChanged: (text: String) -> Unit = {},
    onCancelClick: () -> Unit,
    onClearClick: () -> Unit
) {
//    val keyboardController = LocalSoftwareKeyboardController.current
    TopAppBar(
        title = {
            SearchInput(
                text = searchFilter,
                onTextChanged = onSearchFilterChanged,
                onClearClick = onClearClick
            )
        },
        navigationIcon = {
            IconButton(onClick = { onCancelClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
private fun SearchInput(
    text: String,
    onTextChanged: (text: String) -> Unit,
    onClearClick: () -> Unit
) {
    TextField(
        value = text,
        textStyle = MaterialTheme.typography.subtitle1,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        onValueChange = { onTextChanged(it) },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(
                    modifier = Modifier
                        .size(24.dp)
                        .background(color = Color.Gray, shape = CircleShape),
                    onClick = { onClearClick() }
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.Close, contentDescription = null
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_notes),
                color = Color.White.copy(alpha = 0.75f)
            )
        }

//        activeColor = Color.Transparent,
//        inactiveColor = Color.Transparent,
//        backgroundColor = Color.Transparent
    )
}
