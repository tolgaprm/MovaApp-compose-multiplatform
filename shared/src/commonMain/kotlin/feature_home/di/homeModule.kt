package feature_home.di

import feature_home.data.remote.dataSource.MovieRemoteDataSource
import feature_home.data.remote.dataSource.TvSeriesRemoteDataSource
import feature_home.data.remote.service.HomeService
import feature_home.data.remote.service.HomeServiceImpl
import feature_home.data.repository.HomeMovieRepoImpl
import feature_home.data.repository.HomeTvSeriesRepositoryImpl
import feature_home.domain.repository.HomeMovieRepository
import feature_home.domain.repository.HomeTvSeriesRepository
import feature_home.domain.usecase.HomeUseCases
import feature_home.domain.usecase.movie.GetNowPlayingMoviesUseCase
import feature_home.domain.usecase.movie.GetPopularMoviesUseCase
import feature_home.domain.usecase.movie.GetTopRatedMoviesUseCase
import feature_home.domain.usecase.tv.GetPopularTvSeriesUseCase
import feature_home.domain.usecase.tv.GetTopRatedTvSeriesUseCase
import feature_home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val homeModule = module {
    single<HomeService> { HomeServiceImpl(get(), get()) }
    moduleForMovies()
    moduleForTvSeries()
    moduleUseCases()

    // ScreenModel
    factory { HomeViewModel(get()) }
}

private fun Module.moduleForMovies() {
    single { MovieRemoteDataSource(get()) }
    single<HomeMovieRepository> { HomeMovieRepoImpl(get()) }
}

private fun Module.moduleForTvSeries() {
    single { TvSeriesRemoteDataSource(get()) }
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