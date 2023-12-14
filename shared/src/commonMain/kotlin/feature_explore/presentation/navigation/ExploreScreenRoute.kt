package feature_explore.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import feature_explore.presentation.ExploreScreen
import feature_explore.presentation.ExploreViewModel

class ExploreScreenRoute : Screen {

    @Composable
    override fun Content() {
        val exploreViewModel = viewModel<ExploreViewModel>()
        val exploreUiState = exploreViewModel.state.collectAsStateWithLifecycleM()
        val multiSearchPagingData =
            exploreUiState.multiSearchFlowPagingData.collectAsLazyPagingItems()

        ExploreScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = exploreUiState,
            multiSearchPagingData = multiSearchPagingData,
            onEvent = exploreViewModel::onEvent
        )
    }
}