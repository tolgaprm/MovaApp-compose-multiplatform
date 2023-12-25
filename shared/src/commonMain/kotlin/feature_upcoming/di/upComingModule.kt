package feature_upcoming.di

import feature_upcoming.data.remote.dataSource.UpComingMovieRemoteDataSource
import feature_upcoming.data.remote.service.UpComingServiceImpl
import feature_upcoming.data.remote.service.UpcomingService
import feature_upcoming.data.repository.UpcomingMovieRepositoryImpl
import feature_upcoming.domain.repository.UpcomingMovieRepository
import feature_upcoming.domain.usecase.GetUpcomingMovieUseCase
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