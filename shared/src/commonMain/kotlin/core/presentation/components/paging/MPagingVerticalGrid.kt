package core.presentation.components.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.presentation.theme.dimensions

@Composable
fun <T : Any> MPagingVerticalGrid(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<T>?,
    columns: GridCells.Adaptive = GridCells.Adaptive(170.dp),
    isShowAppendLoading: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.dimensions.fourLevel),
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
    reverseLayout: Boolean = false,
    addItemOnTop: LazyGridScope.() -> Unit = {},
    itemContent: @Composable (T) -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        reverseLayout = reverseLayout
    ) {
        addItemOnTop()

        createLazyPagingList(
            pagingItems = pagingItems,
            itemContent = itemContent,
            isShowAppendLoading = isShowAppendLoading
        )
    }
}