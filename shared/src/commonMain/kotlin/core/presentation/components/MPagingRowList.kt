package core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
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

        pagingItems.loadState.apply {
            when {
                refresh is LoadStateNotLoading && pagingItems.itemCount < 1 -> {
                    item {
                        NotLoadingStateView(modifier = Modifier.fillParentMaxSize())
                    }
                }

                refresh is LoadStateLoading -> {
                    item {
                        MCircularProgressIndicator(
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                }

                refresh is LoadStateError -> {
                    item {
                        ErrorView(
                            modifier = Modifier.fillParentMaxSize(),
                            errorMessage = (refresh as LoadStateError).error.message,
                            onClickRetry = { pagingItems.retry() }
                        )
                    }
                }

                append is LoadStateLoading -> {
                    item {
                        MCircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
private fun NotLoadingStateView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No items found",
            textAlign = TextAlign.Center
        )
    }
}