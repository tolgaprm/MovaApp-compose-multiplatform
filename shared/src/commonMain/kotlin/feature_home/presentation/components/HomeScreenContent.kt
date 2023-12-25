package feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries
import core.presentation.theme.dimensions
import feature_home.presentation.components.section.MoviesSectionWithTitle
import feature_home.presentation.components.section.NowPlayingSection
import feature_home.presentation.components.section.TvSeriesSectionWithTitle

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
        contentPadding = PaddingValues(MaterialTheme.dimensions.fourLevel),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel)
    ) {
        NowPlayingSection(
            nowPlayingMovies = nowPlayingMovies,
            onClickedMovie = onClickedMovie
        )

        MoviesSectionWithTitle(
            movies = popularMovies,
            title = "Popular Movies",
            onClicked = onClickedMovie
        )

        TvSeriesSectionWithTitle(
            tvSeriesPagingData = popularTvSeries,
            title = "Popular Tv Series",
            onClicked = onClickedTvSeries
        )

        MoviesSectionWithTitle(
            movies = topRatedMovies,
            title = "Top Rated Movies",
            onClicked = onClickedMovie
        )

        TvSeriesSectionWithTitle(
            tvSeriesPagingData = topRatedTvSeries,
            title = "Top Rated Tv Series",
            onClicked = onClickedTvSeries
        )
    }
}