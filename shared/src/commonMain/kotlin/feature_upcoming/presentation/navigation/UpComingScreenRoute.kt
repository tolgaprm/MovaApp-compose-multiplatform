package feature_upcoming.presentation.navigation

import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import core.presentation.util.viewModel
import feature_upcoming.presentation.UpComingScreen
import feature_upcoming.presentation.UpComingViewModel

object UpComingScreenRoute : Screen {

    @Composable
    override fun Content() {
        val upcomingViewModel = viewModel<UpComingViewModel>()
        val moviePagingItems = upcomingViewModel.upcomingMovies.data?.collectAsLazyPagingItems()

        UpComingScreen(
            pagingMovies = moviePagingItems
        )
    }
}