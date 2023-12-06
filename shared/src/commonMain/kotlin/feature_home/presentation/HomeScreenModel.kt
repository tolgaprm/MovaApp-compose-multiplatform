package feature_home.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import core.domain.genre.tv.usecase.GetTopRatedTvSeriesUseCase
import feature_home.domain.movie.usecase.GetNowPlayingMoviesUseCase
import feature_home.domain.movie.usecase.GetPopularMoviesUseCase
import feature_home.domain.movie.usecase.GetTopRatedMoviesUseCase
import feature_home.domain.tv.usecase.GetPopularTvSeriesUseCase

class HomeScreenModel(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    getPopularTvSeriesUseCase: GetPopularTvSeriesUseCase,
    getTopRatedTvSeriesUseCase: GetTopRatedTvSeriesUseCase
) : ScreenModel {
    val nowPlayingMovies = getNowPlayingMoviesUseCase()
    val popularMovies = getPopularMoviesUseCase()
    val topRatedMovies = getTopRatedMoviesUseCase()
    val popularTvSeries = getPopularTvSeriesUseCase()
    val topRatedTvSeries = getTopRatedTvSeriesUseCase()
}