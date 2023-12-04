package feature_home.di

import feature_home.data.movie.remote.MovieRemoteDataSource
import feature_home.data.movie.repository.HomeMovieRepoImpl
import feature_home.domain.repository.HomeMovieRepository
import feature_home.domain.usecase.GetNowPlayingMoviesUseCase
import feature_home.domain.usecase.GetPopularMoviesUseCase
import feature_home.domain.usecase.GetTopRatedMoviesUseCase
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