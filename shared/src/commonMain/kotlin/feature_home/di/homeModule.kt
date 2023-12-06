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
import feature_home.presentation.HomeScreenModel
import org.koin.dsl.module

val homeModule = module {
    // Movie
    single { MovieRemoteDataSource(get(), get()) }
    single<HomeMovieRepository> { HomeMovieRepoImpl(get()) }
    factory { GetNowPlayingMoviesUseCase() }
    factory { GetPopularMoviesUseCase() }
    factory { GetTopRatedMoviesUseCase() }

    // TvSeries
    single { TvSeriesRemoteDataSource(get(), get()) }
    single<HomeTvSeriesRepository> { HomeTvSeriesRepositoryImpl(get()) }
    factory { GetPopularTvSeriesUseCase() }
    factory { GetTopRatedTvSeriesUseCase() }

    // ScreenModel
    factory { HomeScreenModel(get(), get(), get(), get(), get()) }
}