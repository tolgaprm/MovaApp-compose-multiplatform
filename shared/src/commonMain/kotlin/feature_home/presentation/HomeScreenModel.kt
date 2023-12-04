package feature_home.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import feature_home.domain.usecase.GetNowPlayingMoviesUseCase
import feature_home.domain.usecase.GetPopularMoviesUseCase
import feature_home.domain.usecase.GetTopRatedMoviesUseCase

class HomeScreenModel(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ScreenModel {
    val nowPlayingMovies = getNowPlayingMoviesUseCase()
    val popularMovies = getPopularMoviesUseCase()
    val getTopRatedMoviesUseCase = getTopRatedMoviesUseCase()
}