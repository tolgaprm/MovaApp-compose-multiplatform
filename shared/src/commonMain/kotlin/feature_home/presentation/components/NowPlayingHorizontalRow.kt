package feature_home.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.presentation.components.NotLoadingStateView
import core.presentation.components.paging.HandlePagingLoadState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NowPlayingHorizontalRowPager(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    nowPlayingPagingItems: LazyPagingItems<Movie>? = null,
    onClickMovie: (Movie) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = paddingValues,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        if (nowPlayingPagingItems == null) {
            item {
                NotLoadingStateView(modifier = Modifier.fillMaxSize())
            }
            return@LazyRow
        }

        item {
            Box(modifier = modifier.fillParentMaxWidth().height(300.dp)) {
                val pagerState = rememberPagerState { nowPlayingPagingItems.itemCount }
                HorizontalPager(
                    state = pagerState,
                    modifier = modifier.fillMaxWidth()
                ) { pagerIndex ->
                    NowPlayingItem(
                        modifier = Modifier.fillMaxWidth(),
                        movie = nowPlayingPagingItems[pagerIndex] ?: return@HorizontalPager,
                        onClickMovie = onClickMovie
                    )
                }

                HandlePagingLoadState(
                    pagingItems = nowPlayingPagingItems
                )
            }
        }
    }
}