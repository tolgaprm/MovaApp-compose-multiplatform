package feature_home.presentation.components.section

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import feature_home.presentation.components.NowPlayingHorizontalRowPager

@Suppress("functionName")
fun LazyListScope.NowPlayingSection(
    modifier: Modifier = Modifier,
    nowPlayingMovies: LazyPagingItems<Movie>?,
    onClickedMovie: (Movie) -> Unit
) {
    item {
        HomeFilmSection(
            modifier = modifier,
            title = "Now Playing",
            onClickSeeAll = {},
        ) {
            NowPlayingHorizontalRowPager(
                modifier = modifier.fillMaxSize(),
                nowPlayingPagingItems = nowPlayingMovies,
                onClickMovie = onClickedMovie
            )
        }
    }
}
