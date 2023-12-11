package feature_detail.presentation

import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_detail.domain.movie.MovieDetailRepository
import feature_detail.domain.tv.TvSeriesDetailRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieDetailRepository: MovieDetailRepository,
    private val tvSeriesDetailRepository: TvSeriesDetailRepository
) :
    BaseViewModel<DetailScreenUiState, DetailScreenEvent>(DetailScreenUiState.Loading) {
    override fun onEvent(event: DetailScreenEvent) {
        when (event) {
            is DetailScreenEvent.GetMovieDetail -> {
                event.id?.let { getMovieDetail(it) } ?: return
            }

            is DetailScreenEvent.GetTvSeriesDetail -> {
                event.id?.let { getTvSeriesDetail(it) } ?: return
            }

            DetailScreenEvent.Retry -> {
                val state = (state.value as? DetailScreenUiState.Error)
                state?.let { errorState ->
                    errorState.movieId?.let {
                        getMovieDetail(it)
                    }
                    errorState.tvSeriesId?.let {
                        getTvSeriesDetail(it)
                    }
                }
            }
        }
    }

    private fun getMovieDetail(id: Int) {
        mutableState.update { DetailScreenUiState.Loading }
        viewModelScope.launch {
            handleResourceWithCallbacks(
                resourceSupplier = {
                    movieDetailRepository.getMovieDetail(id = id)
                },
                onSuccessCallback = { movieDetail ->
                    mutableState.update {
                        DetailScreenUiState.MovieSuccess(
                            movieDetail = movieDetail
                        )
                    }
                },
                onErrorCallback = { errorMessage ->
                    mutableState.update {
                        DetailScreenUiState.Error(
                            message = errorMessage,
                            movieId = id,
                            tvSeriesId = null
                        )
                    }
                }
            )
        }
    }

    private fun getTvSeriesDetail(id: Int) {
        mutableState.update { DetailScreenUiState.Loading }
        viewModelScope.launch {
            handleResourceWithCallbacks(
                resourceSupplier = {
                    tvSeriesDetailRepository.getTvSeriesDetail(id = id)
                },
                onSuccessCallback = { tvDetail ->
                    mutableState.update {
                        DetailScreenUiState.TvSeriesSuccess(
                            tvSeriesDetail = tvDetail
                        )
                    }
                },
                onErrorCallback = { errorMessage ->
                    mutableState.update {
                        DetailScreenUiState.Error(
                            message = errorMessage,
                            movieId = null,
                            tvSeriesId = id
                        )
                    }
                }
            )
        }
    }
}