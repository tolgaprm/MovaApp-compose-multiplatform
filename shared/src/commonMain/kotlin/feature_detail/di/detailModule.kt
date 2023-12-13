package feature_detail.di

import feature_detail.data.movie.MovieDetailRepositoryImpl
import feature_detail.data.movie.remote.MovieDetailRemoteDataSource
import feature_detail.data.service.DetailService
import feature_detail.data.service.DetailServiceImpl
import feature_detail.data.tv.TvSeriesDetailRepositoryImpl
import feature_detail.data.tv.remote.TvSeriesDetailRemoteDataSource
import feature_detail.domain.movie.MovieDetailRepository
import feature_detail.domain.tv.TvSeriesDetailRepository
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