package feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import feature_home.presentation.components.section.MoviesSection
import feature_home.presentation.components.section.NowPlayingSection
import feature_home.presentation.components.section.TvSeriesSection

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    nowPlayingMovies: LazyPagingItems<Movie>?,
    popularMovies: LazyPagingItems<Movie>?,
    topRatedMovies: LazyPagingItems<Movie>?,
    popularTvSeries: LazyPagingItems<TvSeries>?,
    topRatedTvSeries: LazyPagingItems<TvSeries>?,
    onClickedMovie: (Movie) -> Unit,
    onClickedTvSeries: (TvSeries) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NowPlayingSection(
            nowPlayingMovies = nowPlayingMovies,
            onClickedMovie = onClickedMovie
        )

        MoviesSection(
            movies = popularMovies,
            title = "Popular Movies",
            onClicked = onClickedMovie
        )

        TvSeriesSection(
            tvSeriesPagingData = popularTvSeries,
            title = "Popular Tv Series",
            onClicked = onClickedTvSeries
        )

        MoviesSection(
            movies = topRatedMovies,
            title = "Top Rated Movies",
            onClicked = onClickedMovie
        )

        TvSeriesSection(
            tvSeriesPagingData = topRatedTvSeries,
            title = "Top Rated Tv Series",
            onClicked = onClickedTvSeries
        )
    }
}