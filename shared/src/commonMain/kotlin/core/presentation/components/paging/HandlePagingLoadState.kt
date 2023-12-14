package core.presentation.components.paging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import core.presentation.components.ErrorView
import core.presentation.components.MCircularProgressIndicator
import core.presentation.components.NotLoadingStateView

@Composable
fun <T : Any> HandlePagingLoadState(
    pagingItems: LazyPagingItems<T>,
    isShowAppendLoading: Boolean = true,
) {
    Box(
        modifier = Modifier.fillMaxSize()
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
                    if (isShowAppendLoading) {
                        MCircularProgressIndicator()
                    }
                }
            }
        }
    }
}