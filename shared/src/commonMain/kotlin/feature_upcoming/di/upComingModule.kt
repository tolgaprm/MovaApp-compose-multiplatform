package feature_upcoming.di

import feature_upcoming.data.movie.UpcomingMovieRepositoryImpl
import feature_upcoming.data.movie.remote.UpComingMovieRemoteDataSource
import feature_upcoming.data.service.UpComingServiceImpl
import feature_upcoming.data.service.UpcomingService
import feature_upcoming.domain.movie.UpcomingMovieRepository
import feature_upcoming.domain.movie.usecase.GetUpcomingMovieUseCase
import feature_upcoming.presentation.UpComingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val upcomingModule = module {
    single<UpcomingService> { UpComingServiceImpl(get(), get()) }
    modulesForMovies()
    factory { UpComingViewModel(get()) }
}

private fun Module.modulesForMovies() {
    single { UpComingMovieRemoteDataSource(get()) }
    single<UpcomingMovieRepository> { UpcomingMovieRepositoryImpl(get()) }
    factory { GetUpcomingMovieUseCase() }
}