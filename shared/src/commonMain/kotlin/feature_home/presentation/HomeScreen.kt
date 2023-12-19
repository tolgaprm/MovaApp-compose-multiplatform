package feature_home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import core.presentation.base.MovaInfoBottomSheetScaffold
import core.presentation.base.expandBottomSheet
import core.presentation.base.hideBottomSheet
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
    selectedMovie: Movie? = null,
    selectedTvSeries: TvSeries? = null,
    onNavigateToDetails: () -> Unit,
    onEvent: (HomeScreenEvent) -> Unit
) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val coroutineScope = rememberCoroutineScope()


    MovaInfoBottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        onClickCloseBottomSheet = {
            coroutineScope.hideBottomSheet(bottomSheetScaffoldState)
        },
        scaffoldState = bottomSheetScaffoldState,
        selectedMovie = selectedMovie,
        selectedTvSeries = selectedTvSeries,
        onClickedDetails = {
            coroutineScope.hideBottomSheet(bottomSheetScaffoldState)
            onNavigateToDetails()
        },
        content = {
            HomeScreenContent(
                modifier = Modifier.padding(it),
                nowPlayingMovies = nowPlayingMovies,
                popularMovies = popularMovies,
                topRatedMovies = topRatedMovies,
                popularTvSeries = popularTvSeries,
                topRatedTvSeries = topRatedTvSeries,
                onClickedMovie = { movie ->
                    onEvent(HomeScreenEvent.OnMovieSelected(movie))
                    coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                },
                onClickedTvSeries = { tvSeries ->
                    onEvent(HomeScreenEvent.OnTvSeriesSelected(tvSeries))
                    coroutineScope.expandBottomSheet(bottomSheetScaffoldState)
                }
            )
        },
    )
}