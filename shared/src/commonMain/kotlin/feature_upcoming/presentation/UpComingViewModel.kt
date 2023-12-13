package feature_upcoming.presentation

import core.presentation.util.ViewModel
import feature_upcoming.domain.movie.usecase.GetUpcomingMovieUseCase

class UpComingViewModel(
    getUpcomingMovieUseCase: GetUpcomingMovieUseCase
) : ViewModel {
    val upcomingMovies = getUpcomingMovieUseCase()
}