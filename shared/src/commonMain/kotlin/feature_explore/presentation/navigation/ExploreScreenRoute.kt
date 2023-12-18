package feature_explore.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import feature_detail.presentation.navigation.DetailScreenRoute
import feature_explore.presentation.ExploreScreen
import feature_explore.presentation.ExploreViewModel

class ExploreScreenRoute : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val exploreViewModel = viewModel<ExploreViewModel>()
        val exploreUiState = exploreViewModel.uiState.collectAsStateWithLifecycleM()

        ExploreScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = exploreUiState,
            onEvent = exploreViewModel::onEvent,
            onNavigateToPersonDetail = {
                // TODO navigate to person detail
            },
            onNavigateToDetail = {
                navigator.push(
                    DetailScreenRoute(
                        movieId = exploreUiState.selectedMovie?.id,
                        tvSeriesId = exploreUiState.selectedTvSeries?.id
                    )
                )
            }
        )
    }
}