package core.presentation.components.paging

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.presentation.components.NotLoadingStateView

fun <T : Any> LazyListScope.createLazyPagingList(
    pagingItems: LazyPagingItems<T>?,
    isShowAppendLoading: Boolean = true,
    itemContent: @Composable (T) -> Unit
) {
    if (pagingItems == null) {
        item {
            NotLoadingStateView(modifier = Modifier.fillParentMaxSize())
        }
        return
    }

    items(pagingItems.itemCount) { index ->
        pagingItems[index]?.let { item ->
            itemContent(item)
        }

        HandlePagingLoadState(
            pagingItems = pagingItems,
            isShowAppendLoading = isShowAppendLoading,
        )
    }
}

fun <T : Any> LazyGridScope.createLazyPagingList(
    pagingItems: LazyPagingItems<T>?,
    isShowAppendLoading: Boolean = true,
    itemContent: @Composable (T) -> Unit
) {
    if (pagingItems == null) {
        item {
            NotLoadingStateView(modifier = Modifier.fillMaxSize())
        }
        return
    }

    items(pagingItems.itemCount) { index ->
        pagingItems[index]?.let { item ->
            itemContent(item)
        }

        HandlePagingLoadState(
            pagingItems = pagingItems,
            isShowAppendLoading = isShowAppendLoading,
        )
    }
}