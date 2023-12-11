package feature_detail.presentation

import cafe.adriel.voyager.core.model.screenModelScope
import core.presentation.base.BaseScreenModel
import feature_detail.domain.movie.MovieDetailRepository
import feature_detail.domain.tv.TvSeriesDetailRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailScreenModel(
    private val movieDetailRepository: MovieDetailRepository,
    private val tvSeriesDetailRepository: TvSeriesDetailRepository
) :
    BaseScreenModel<DetailScreenUiState, DetailScreenEvent>(DetailScreenUiState.Loading) {
    override fun onEvent(event: DetailScreenEvent) {
        when (event) {
            is DetailScreenEvent.GetMovieDetail -> {
                event.id?.let { getMovieDetail(it) } ?: return
            }

            is DetailScreenEvent.GetTvSeriesDetail -> {
                event.id?.let { getTvSeriesDetail(it) } ?: return
            }
        }
    }

    private fun getMovieDetail(id: Int) {
        mutableState.update { DetailScreenUiState.Loading }
        screenModelScope.launch {
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
                            message = errorMessage
                        )
                    }
                }
            )
        }
    }

    private fun getTvSeriesDetail(id: Int) {
        mutableState.update { DetailScreenUiState.Loading }
        screenModelScope.launch {
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
                            message = errorMessage
                        )
                    }
                }
            )
        }
    }
}