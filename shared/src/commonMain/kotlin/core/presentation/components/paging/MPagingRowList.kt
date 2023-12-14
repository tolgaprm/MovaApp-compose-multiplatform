package core.presentation.components.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.presentation.theme.dimensions

@Composable
fun <T : Any> MPagingRowList(
    pagingItems: LazyPagingItems<T>?,
    paddingValues: PaddingValues = PaddingValues(),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel),
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = paddingValues,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        createLazyPagingList(
            pagingItems = pagingItems,
            itemContent = itemContent
        )
    }
}