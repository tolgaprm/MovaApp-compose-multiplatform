package feature_home.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import feature_detail.presentation.navigation.DetailScreenRoute
import feature_home.presentation.HomeScreen
import feature_home.presentation.HomeScreenEvent
import feature_home.presentation.HomeViewModel
import kotlinx.coroutines.launch

class HomeScreenRoute : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val homeViewModel = viewModel<HomeViewModel>()
        val nowPlayingMovies = homeViewModel.nowPlayingMovies.data?.collectAsLazyPagingItems()
        val popularMovies = homeViewModel.popularMovies.data?.collectAsLazyPagingItems()
        val topRatedMovies = homeViewModel.topRatedMovies.data?.collectAsLazyPagingItems()
        val popularTvSeries = homeViewModel.popularTvSeries.data?.collectAsLazyPagingItems()
        val topRatedTvSeries = homeViewModel.topRatedTvSeries.data?.collectAsLazyPagingItems()

        val uiState = homeViewModel.state.collectAsStateWithLifecycleM()

        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(
                skipHiddenState = false
            )
        )
        val coroutineScope = rememberCoroutineScope()

        HomeScreen(nowPlayingMovies = nowPlayingMovies,
            popularMovies = popularMovies,
            topRatedMovies = topRatedMovies,
            popularTvSeries = popularTvSeries,
            topRatedTvSeries = topRatedTvSeries,
            scaffoldState = bottomSheetScaffoldState,
            selectedMovie = uiState.selectedMovie,
            selectedTvSeries = uiState.selectedTvSeries,
            onClickedMovie = { movie ->
                homeViewModel.onEvent(HomeScreenEvent.OnMovieSelected(movie))
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            onClickedTvSeries = { tvSeries ->
                homeViewModel.onEvent(HomeScreenEvent.OnTvSeriesSelected(tvSeries))
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            onClickCloseBottomSheet = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.hide()
                }
            },
            onClickedDetails = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.hide()
                }
                navigator.push(
                    DetailScreenRoute(
                        movieId = uiState.selectedMovie?.id,
                        tvSeriesId = uiState.selectedTvSeries?.id
                    )
                )
            }
        )
    }
}
