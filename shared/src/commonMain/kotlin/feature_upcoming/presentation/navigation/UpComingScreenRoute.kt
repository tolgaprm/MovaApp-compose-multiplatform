package feature_upcoming.presentation.navigation

import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import feature_detail.presentation.navigation.DetailScreenRoute
import feature_upcoming.presentation.UpComingScreen
import feature_upcoming.presentation.UpComingViewModel

object UpComingScreenRoute : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val upcomingViewModel = viewModel<UpComingViewModel>()
        val upcomingScreenUiState = upcomingViewModel.state.collectAsStateWithLifecycleM()
        val moviePagingItems = upcomingViewModel.upcomingMovies?.collectAsLazyPagingItems()

        UpComingScreen(
            pagingMovies = moviePagingItems,
            uiState = upcomingScreenUiState,
            onEvent = upcomingViewModel::onEvent,
            navigateToDetailScreen = {
                navigator.push(
                    DetailScreenRoute(
                        movieId = upcomingScreenUiState.selectedMovie?.id ?: 0,
                    )
                )
            },
        )
    }
}