package feature_home.di

import feature_home.data.movie.MovieRemoteDataSource
import feature_home.data.movie.HomeMovieRepoImpl
import feature_home.domain.movie.HomeMovieRepository
import feature_home.domain.movie.usecase.GetNowPlayingMoviesUseCase
import feature_home.domain.movie.usecase.GetPopularMoviesUseCase
import feature_home.domain.movie.usecase.GetTopRatedMoviesUseCase
import feature_home.presentation.HomeScreenModel
import org.koin.dsl.module

val homeModule = module {
    single { MovieRemoteDataSource(get(), get()) }
    single<HomeMovieRepository> { HomeMovieRepoImpl(get()) }
    factory { GetNowPlayingMoviesUseCase() }
    factory { GetPopularMoviesUseCase() }
    factory { GetTopRatedMoviesUseCase() }
    factory { HomeScreenModel(get(), get(), get()) }
}