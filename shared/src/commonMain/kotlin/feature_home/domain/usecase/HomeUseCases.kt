package feature_home.domain.usecase

import feature_home.domain.usecase.movie.GetNowPlayingMoviesUseCase
import feature_home.domain.usecase.movie.GetPopularMoviesUseCase
import feature_home.domain.usecase.movie.GetTopRatedMoviesUseCase
import feature_home.domain.usecase.tv.GetPopularTvSeriesUseCase
import feature_home.domain.usecase.tv.GetTopRatedTvSeriesUseCase

data class HomeUseCases(
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    val getPopularTvSeriesUseCase: GetPopularTvSeriesUseCase,
    val getTopRatedTvSeriesUseCase: GetTopRatedTvSeriesUseCase
)
