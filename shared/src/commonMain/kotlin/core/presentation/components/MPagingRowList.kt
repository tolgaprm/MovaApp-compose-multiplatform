package core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> MPagingRowList(
    pagingItems: LazyPagingItems<T>?,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = paddingValues,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        if (pagingItems == null) {
            item {
                NotLoadingStateView(modifier = Modifier.fillParentMaxSize())
            }
            return@LazyRow
        }

        items(pagingItems.itemCount) { index ->
            pagingItems[index]?.let { item ->
                itemContent(item)
            }
        }
    }
}