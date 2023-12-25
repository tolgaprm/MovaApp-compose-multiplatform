package feature_upcoming.presentation

import androidx.paging.cachedIn
import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_upcoming.domain.usecase.GetUpcomingMovieUseCase
import kotlinx.coroutines.flow.update

class UpComingViewModel(
    getUpcomingMovieUseCase: GetUpcomingMovieUseCase
) : BaseViewModel<UpComingUiState, UpComingScreenEvent>(UpComingUiState()) {
    val upcomingMovies = getUpcomingMovieUseCase().data?.cachedIn(viewModelScope)
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