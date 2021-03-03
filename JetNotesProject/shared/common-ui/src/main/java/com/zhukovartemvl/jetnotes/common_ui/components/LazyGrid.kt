package com.zhukovartemvl.jetnotes.common_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun <T> LazyGrid(
    items: List<T> = listOf(),
    rows: Int = 3,
    hPadding: Int = 8,
    bottomPadding: Int = 80,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val chunkedList = items.chunked(rows)
    LazyColumn(modifier = Modifier.padding(horizontal = hPadding.dp)) {
        itemsIndexed(chunkedList) { index: Int, rowList: List<T> ->
            if (index == 0) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            Row {
                rowList.forEachIndexed { rowIndex, item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.Top)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        itemContent(item, index * rows + rowIndex)
                    }
                }
                repeat(rows - rowList.size) {
                    Box(modifier = Modifier.weight(1f).padding(8.dp)) {}
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(bottomPadding.dp))
        }
    }
}
