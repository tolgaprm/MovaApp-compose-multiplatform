package feature_detail.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import feature_detail.presentation.DetailScreen
import feature_detail.presentation.DetailScreenEvent
import feature_detail.presentation.DetailViewModel

data class DetailScreenRoute(
    val movieId: Int? = null,
    val tvSeriesId: Int? = null
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val detailViewModel = viewModel<DetailViewModel>()
        val uiState = detailViewModel.state.collectAsStateWithLifecycleM()

        DetailScreen(
            uiState = uiState,
            onBackPressed = navigator::pop,
            onEvent = detailViewModel::onEvent
        )

        LaunchedEffect(movieId, tvSeriesId) {
            movieId?.let {
                detailViewModel.onEvent(DetailScreenEvent.GetMovieDetail(it))
            }

            tvSeriesId?.let {
                detailViewModel.onEvent(DetailScreenEvent.GetTvSeriesDetail(it))
            }
        }
    }
}