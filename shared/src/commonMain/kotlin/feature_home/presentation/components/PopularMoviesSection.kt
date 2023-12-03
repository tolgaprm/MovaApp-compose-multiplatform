package feature_home.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.models.Movie
import core.presentation.components.MPagingRowList

@Suppress("functionName")
fun LazyListScope.PopularMoviesSection(
    modifier: Modifier = Modifier,
    popularMovies: LazyPagingItems<Movie>?
) {
    item {
        HomeFilmSection(
            modifier = modifier,
            title = "Popular Movies",
            onClickSeeAll = {}
        ) {
            MPagingRowList(
                pagingItems = popularMovies,
                modifier = Modifier.fillMaxSize()
            ) { movie ->
                Text(movie.title)
            }
        }
    }
}