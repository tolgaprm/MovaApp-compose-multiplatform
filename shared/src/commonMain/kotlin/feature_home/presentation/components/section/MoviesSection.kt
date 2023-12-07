package feature_home.presentation.components.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import core.presentation.components.MPagingRowList
import core.presentation.components.MovieItem
import core.presentation.components.TvSeriesItem
import core.presentation.theme.dimensions

@Suppress("functionName")
fun LazyListScope.MoviesSectionWithTitle(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>?,
    title: String,
    onClicked: (Movie) -> Unit
) {
    item {
        HomeFilmSection(
            modifier = modifier,
            title = title,
            onClickSeeAll = {}
        ) {
            MPagingRowList(
                pagingItems = movies,
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.threeLevel)
            ) { movie ->
                MovieItem(
                    modifier = Modifier.clickable { onClicked(movie) },
                    movie = movie
                )
            }
        }
    }
}

@Suppress("functionName")
fun LazyListScope.TvSeriesSectionWithTitle(
    modifier: Modifier = Modifier,
    tvSeriesPagingData: LazyPagingItems<TvSeries>?,
    title: String,
    onClicked: (TvSeries) -> Unit
) {
    item {
        HomeFilmSection(
            modifier = modifier,
            title = title,
            onClickSeeAll = {}
        ) {
            MPagingRowList(
                pagingItems = tvSeriesPagingData,
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.threeLevel)
            ) { tvSeries ->
                TvSeriesItem(
                    modifier = Modifier.clickable { onClicked(tvSeries) },
                    tvSeries = tvSeries
                )
            }
        }
    }
}