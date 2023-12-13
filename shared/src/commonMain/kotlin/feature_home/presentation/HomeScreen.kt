package feature_home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import feature_home.presentation.components.HomeScreenBottomSheet
import feature_home.presentation.components.HomeScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    nowPlayingMovies: LazyPagingItems<Movie>?,
    popularMovies: LazyPagingItems<Movie>?,
    topRatedMovies: LazyPagingItems<Movie>?,
    popularTvSeries: LazyPagingItems<TvSeries>?,
    topRatedTvSeries: LazyPagingItems<TvSeries>?,
    scaffoldState: BottomSheetScaffoldState,
    selectedMovie: Movie? = null,
    selectedTvSeries: TvSeries? = null,
    onClickedMovie: (Movie) -> Unit,
    onClickedTvSeries: (TvSeries) -> Unit,
    onClickCloseBottomSheet: () -> Unit,
    onClickedDetails: () -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        sheetPeekHeight = 0.dp,
        sheetContent = {
            HomeScreenBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                selectedMovie = selectedMovie,
                selectedTvSeries = selectedTvSeries,
                onClickClose = onClickCloseBottomSheet,
                onClickedDetails = onClickedDetails
            )
        },
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetContentColor = MaterialTheme.colorScheme.onBackground,
        scaffoldState = scaffoldState,
        content = {
            HomeScreenContent(
                modifier = Modifier.padding(it),
                nowPlayingMovies = nowPlayingMovies,
                popularMovies = popularMovies,
                topRatedMovies = topRatedMovies,
                popularTvSeries = popularTvSeries,
                topRatedTvSeries = topRatedTvSeries,
                onClickedMovie = onClickedMovie,
                onClickedTvSeries = onClickedTvSeries
            )
        }
    )
}