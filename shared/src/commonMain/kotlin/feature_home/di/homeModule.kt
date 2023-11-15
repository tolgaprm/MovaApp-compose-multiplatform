package feature_home.di

import feature_home.data.movie.remote.MovieRemoteDataSource
import feature_home.data.movie.repository.HomeMovieRepoImpl
import feature_home.domain.repository.HomeMovieRepository
import org.koin.dsl.module

val homeModule = module {
    single { MovieRemoteDataSource(get()) }
    single<HomeMovieRepository> { HomeMovieRepoImpl(get()) }
}