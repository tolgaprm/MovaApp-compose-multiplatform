package core.presentation.components.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> MPagingVerticalGrid(
    modifier: Modifier = Modifier,
    columns: GridCells,
    pagingItems: LazyPagingItems<T>?,
    isShowAppendLoading: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    addItemOnTop: LazyGridScope.() -> Unit = {},
    itemContent: @Composable (T) -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
    ) {
        addItemOnTop()

        createLazyPagingList(
            pagingItems = pagingItems,
            itemContent = itemContent,
            isShowAppendLoading = isShowAppendLoading
        )
    }
}