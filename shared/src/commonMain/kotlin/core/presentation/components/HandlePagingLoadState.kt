package core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> BoxScope.HandlePagingLoadState(
    pagingItems: LazyPagingItems<T>,
) {
    pagingItems.loadState.apply {
        when {
            refresh is LoadStateNotLoading && pagingItems.itemCount < 1 -> {
                NotLoadingStateView(modifier = Modifier.fillMaxSize())
            }

            refresh is LoadStateLoading -> {
                MCircularProgressIndicator()
            }

            refresh is LoadStateError -> {
                ErrorView(
                    modifier = Modifier.fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    errorMessage = (refresh as LoadStateError).error.message,
                    onClickRetry = { pagingItems.retry() }
                )
            }

            append is LoadStateLoading -> {
                MCircularProgressIndicator()
            }
        }
    }
}