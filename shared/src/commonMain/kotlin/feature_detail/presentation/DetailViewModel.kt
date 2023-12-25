package feature_detail.presentation

import core.presentation.base.BaseViewModel
import core.presentation.util.viewModelScope
import feature_detail.domain.repository.MovieDetailRepository
import feature_detail.domain.repository.TvSeriesDetailRepository
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

            is DetailScreenEvent.OnClickRecommendationMovie -> {
                mutableState.update {
                    (state.value as? DetailScreenUiState.MovieSuccess)?.copy(
                        selectedRecommendationMovie = event.movie
                    ) as DetailScreenUiState
                }
            }

            is DetailScreenEvent.OnClickRecommendationTvSeries -> {
                mutableState.update {
                    (state.value as? DetailScreenUiState.TvSeriesSuccess)?.copy(
                        selectedRecommendationTvSeries = event.tvSeries
                    ) as DetailScreenUiState
                }
            }
        }
    }

    private fun getMovieDetail(movieId: Int) {
        mutableState.update { DetailScreenUiState.Loading }
        viewModelScope.launch {
            handleResourceWithCallbacks(
                resourceSupplier = {
                    movieDetailRepository.getMovieDetail(id = movieId)
                },
                onSuccessCallback = { movieDetail ->
                    mutableState.update {
                        DetailScreenUiState.MovieSuccess(
                            movieDetail = movieDetail,
                            movieRecommendations = movieDetailRepository.getMovieRecommendations(id = movieId)
                        )
                    }
                },
                onErrorCallback = { errorMessage ->
                    mutableState.update {
                        DetailScreenUiState.Error(
                            message = errorMessage,
                            movieId = movieId,
                            tvSeriesId = null
                        )
                    }
                }
            )
        }
    }

    private fun getTvSeriesDetail(tvSeriesId: Int) {
        mutableState.update { DetailScreenUiState.Loading }
        viewModelScope.launch {
            handleResourceWithCallbacks(
                resourceSupplier = {
                    tvSeriesDetailRepository.getTvSeriesDetail(id = tvSeriesId)
                },
                onSuccessCallback = { tvDetail ->
                    mutableState.update {
                        DetailScreenUiState.TvSeriesSuccess(
                            tvSeriesDetail = tvDetail,
                            tvSeriesRecommendations = tvSeriesDetailRepository.getTvSeriesRecommendations(
                                id = tvSeriesId
                            )
                        )
                    }
                },
                onErrorCallback = { errorMessage ->
                    mutableState.update {
                        DetailScreenUiState.Error(
                            message = errorMessage,
                            movieId = null,
                            tvSeriesId = tvSeriesId
                        )
                    }
                }
            )
        }
    }
}