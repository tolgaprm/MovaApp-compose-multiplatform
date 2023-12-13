package feature_upcoming.presentation

import core.presentation.base.BaseViewModel
import feature_upcoming.domain.movie.usecase.GetUpcomingMovieUseCase
import kotlinx.coroutines.flow.update

class UpComingViewModel(
    getUpcomingMovieUseCase: GetUpcomingMovieUseCase
) : BaseViewModel<UpComingUiState, UpComingScreenEvent>(UpComingUiState()) {
    val upcomingMovies = getUpcomingMovieUseCase()
    override fun onEvent(event: UpComingScreenEvent) {
        when (event) {
            is UpComingScreenEvent.OnClickInfo -> {
                mutableState.update {
                    it.copy(
                        selectedMovie = event.movie
                    )
                }
            }
        }
    }
}