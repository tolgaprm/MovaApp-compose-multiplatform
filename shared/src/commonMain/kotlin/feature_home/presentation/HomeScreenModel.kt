package feature_home.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import feature_home.domain.usecase.GetNowPlayingMoviesUseCase
import feature_home.domain.usecase.GetPopularMoviesUseCase

class HomeScreenModel(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ScreenModel {
    val nowPlayingMovies = getNowPlayingMoviesUseCase()
    val popularMovies = getPopularMoviesUseCase()
}