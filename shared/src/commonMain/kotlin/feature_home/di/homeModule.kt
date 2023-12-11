package feature_home.di

import core.domain.genre.tv.usecase.GetTopRatedTvSeriesUseCase
import feature_home.data.movie.HomeMovieRepoImpl
import feature_home.data.movie.MovieRemoteDataSource
import feature_home.data.tv.HomeTvSeriesRepositoryImpl
import feature_home.data.tv.TvSeriesRemoteDataSource
import feature_home.domain.movie.HomeMovieRepository
import feature_home.domain.movie.usecase.GetNowPlayingMoviesUseCase
import feature_home.domain.movie.usecase.GetPopularMoviesUseCase
import feature_home.domain.movie.usecase.GetTopRatedMoviesUseCase
import feature_home.domain.tv.HomeTvSeriesRepository
import feature_home.domain.tv.usecase.GetPopularTvSeriesUseCase
import feature_home.domain.usecase.HomeUseCases
import feature_home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val homeModule = module {
    moduleForMovies()
    moduleForTvSeries()
    moduleUseCases()

    // ScreenModel
    factory { HomeViewModel(get()) }
}

private fun Module.moduleForMovies() {
    single { MovieRemoteDataSource(get(), get()) }
    single<HomeMovieRepository> { HomeMovieRepoImpl(get()) }
}

private fun Module.moduleForTvSeries() {
    single { TvSeriesRemoteDataSource(get(), get()) }
    single<HomeTvSeriesRepository> { HomeTvSeriesRepositoryImpl(get()) }
}

private fun Module.moduleUseCases() {
    factory {
        HomeUseCases(
            getNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCase(),
            getPopularMoviesUseCase = GetPopularMoviesUseCase(),
            getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(),
            getPopularTvSeriesUseCase = GetPopularTvSeriesUseCase(),
            getTopRatedTvSeriesUseCase = GetTopRatedTvSeriesUseCase()
        )
    }
}