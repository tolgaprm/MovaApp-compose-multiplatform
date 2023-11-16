package feature_home.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import feature_home.domain.usecase.GetNowPlayingMoviesUseCase

class HomeScreenModel(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ScreenModel {
    val nowPlayingMovies = getNowPlayingMoviesUseCase()
}