package feature_upcoming.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.presentation.components.MPagingColumnList
import core.presentation.theme.dimensions
import feature_upcoming.presentation.components.UpComingMovieItem

@Composable
fun UpComingScreen(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>?
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(
                modifier = Modifier.padding(MaterialTheme.dimensions.fourLevel),
                text = "Coming Soon",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    ) {
        MPagingColumnList(
            modifier = Modifier.padding(top = it.calculateTopPadding()).fillMaxSize(),
            pagingItems = pagingMovies,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel),
            paddingValues = PaddingValues(MaterialTheme.dimensions.fourLevel)
        ) { movie ->
            UpComingMovieItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                movie = movie
            )
        }
    }
}