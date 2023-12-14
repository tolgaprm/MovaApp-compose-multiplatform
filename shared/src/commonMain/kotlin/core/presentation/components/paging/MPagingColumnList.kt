package core.presentation.components.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> MPagingColumnList(
    pagingItems: LazyPagingItems<T>?,
    paddingValues: PaddingValues = PaddingValues(),
    isShowAppendLoading: Boolean = true,
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier = Modifier,
    addItemOnTop: LazyListScope.() -> Unit = {},
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = paddingValues,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        addItemOnTop()

        createLazyPagingList(
            pagingItems = pagingItems,
            isShowAppendLoading = isShowAppendLoading,
            itemContent = itemContent
        )
    }
}
