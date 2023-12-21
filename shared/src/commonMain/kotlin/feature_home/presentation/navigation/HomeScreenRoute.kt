package feature_home.presentation.navigation

import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import feature_detail.presentation.navigation.DetailScreenRoute
import feature_home.presentation.HomeScreen
import feature_home.presentation.HomeViewModel

class HomeScreenRoute : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val homeViewModel = viewModel<HomeViewModel>()
        val nowPlayingMovies = homeViewModel.nowPlayingMovies?.collectAsLazyPagingItems()
        val popularMovies = homeViewModel.popularMovies?.collectAsLazyPagingItems()
        val topRatedMovies = homeViewModel.topRatedMovies?.collectAsLazyPagingItems()
        val popularTvSeries = homeViewModel.popularTvSeries?.collectAsLazyPagingItems()
        val topRatedTvSeries = homeViewModel.topRatedTvSeries?.collectAsLazyPagingItems()
        val uiState = homeViewModel.state.collectAsStateWithLifecycleM()

        HomeScreen(
            nowPlayingMovies = nowPlayingMovies,
            popularMovies = popularMovies,
            topRatedMovies = topRatedMovies,
            popularTvSeries = popularTvSeries,
            topRatedTvSeries = topRatedTvSeries,
            selectedMovie = uiState.selectedMovie,
            selectedTvSeries = uiState.selectedTvSeries,
            onNavigateToDetails = {
                navigator.push(
                    DetailScreenRoute(
                        movieId = uiState.selectedMovie?.id,
                        tvSeriesId = uiState.selectedTvSeries?.id
                    )
                )
            },
            onEvent = homeViewModel::onEvent
        )
    }
}
