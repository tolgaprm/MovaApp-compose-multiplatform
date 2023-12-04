package feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.presentation.components.MPagingRowList
import core.presentation.components.MovieItem

@Suppress("functionName")
fun LazyListScope.MoviesSection(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>?,
    title: String,
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
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) { movie ->
                MovieItem(movie = movie)
            }
        }
    }
}