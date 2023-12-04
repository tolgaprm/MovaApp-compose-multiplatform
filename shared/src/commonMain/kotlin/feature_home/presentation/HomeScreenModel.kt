package feature_home.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import feature_home.domain.movie.usecase.GetNowPlayingMoviesUseCase
import feature_home.domain.movie.usecase.GetPopularMoviesUseCase
import feature_home.domain.movie.usecase.GetTopRatedMoviesUseCase

class HomeScreenModel(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ScreenModel {
    val nowPlayingMovies = getNowPlayingMoviesUseCase()
    val popularMovies = getPopularMoviesUseCase()
    val getTopRatedMoviesUseCase = getTopRatedMoviesUseCase()
}