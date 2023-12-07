package feature_home.presentation

import core.presentation.base.BaseScreenModel
import feature_home.domain.usecase.HomeUseCases
import kotlinx.coroutines.flow.update

class HomeScreenModel(
    homeUseCases: HomeUseCases
) : BaseScreenModel<HomeScreenUiState, HomeScreenEvent>(HomeScreenUiState()) {

    val nowPlayingMovies = homeUseCases.getNowPlayingMoviesUseCase()
    val popularMovies = homeUseCases.getPopularMoviesUseCase()
    val topRatedMovies = homeUseCases.getTopRatedMoviesUseCase()
    val popularTvSeries = homeUseCases.getPopularTvSeriesUseCase()
    val topRatedTvSeries = homeUseCases.getTopRatedTvSeriesUseCase()

    override fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnMovieSelected -> {
                mutableState.update {
                    it.copy(
                        selectedMovie = event.movie,
                        selectedTvSeries = null
                    )
                }
            }

            is HomeScreenEvent.OnTvSeriesSelected -> {
                mutableState.update {
                    it.copy(
                        selectedTvSeries = event.tvSeries,
                        selectedMovie = null
                    )
                }
            }
        }
    }
}