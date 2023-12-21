package feature_home.presentation

import androidx.paging.cachedIn
import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_home.domain.usecase.HomeUseCases
import kotlinx.coroutines.flow.update

class HomeViewModel(
    homeUseCases: HomeUseCases
) : BaseViewModel<HomeScreenUiState, HomeScreenEvent>(HomeScreenUiState()) {

    val nowPlayingMovies = homeUseCases.getNowPlayingMoviesUseCase().data?.cachedIn(viewModelScope)
    val popularMovies = homeUseCases.getPopularMoviesUseCase().data?.cachedIn(viewModelScope)
    val topRatedMovies = homeUseCases.getTopRatedMoviesUseCase().data?.cachedIn(viewModelScope)
    val popularTvSeries = homeUseCases.getPopularTvSeriesUseCase().data?.cachedIn(viewModelScope)
    val topRatedTvSeries = homeUseCases.getTopRatedTvSeriesUseCase().data?.cachedIn(viewModelScope)

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