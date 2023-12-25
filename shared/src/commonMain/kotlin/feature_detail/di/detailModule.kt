package feature_detail.di

import feature_detail.data.remote.dataSource.MovieDetailRemoteDataSource
import feature_detail.data.remote.dataSource.TvSeriesDetailRemoteDataSource
import feature_detail.data.remote.service.DetailService
import feature_detail.data.remote.service.DetailServiceImpl
import feature_detail.data.repository.MovieDetailRepositoryImpl
import feature_detail.data.repository.TvSeriesDetailRepositoryImpl
import feature_detail.domain.repository.MovieDetailRepository
import feature_detail.domain.repository.TvSeriesDetailRepository
import feature_detail.presentation.DetailViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val detailModule = module {
    single<DetailService> { DetailServiceImpl(get(), get()) }
    moduleForMovies()
    moduleForTvSeries()

    factory { DetailViewModel(get(), get()) }
}

private fun Module.moduleForMovies() {
    single { MovieDetailRemoteDataSource(get()) }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get()) }
}

private fun Module.moduleForTvSeries() {
    single { TvSeriesDetailRemoteDataSource(get()) }
    single<TvSeriesDetailRepository> { TvSeriesDetailRepositoryImpl(get()) }
}