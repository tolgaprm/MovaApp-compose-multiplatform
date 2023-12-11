package feature_detail.di

import feature_detail.data.remote.movie.MovieDetailRemoteDataSource
import feature_detail.data.remote.movie.MovieDetailRepositoryImpl
import feature_detail.data.remote.tv.TvSeriesDetailRemoteDataSource
import feature_detail.data.remote.tv.TvSeriesDetailRepositoryImpl
import feature_detail.domain.movie.MovieDetailRepository
import feature_detail.domain.tv.TvSeriesDetailRepository
import feature_detail.presentation.DetailScreenModel
import org.koin.core.module.Module
import org.koin.dsl.module

val detailModule = module {
    moduleForMovies()
    moduleForTvSeries()

    factory { DetailScreenModel(get(), get()) }
}

private fun Module.moduleForMovies() {
    single { MovieDetailRemoteDataSource(get(), get()) }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get()) }
}

private fun Module.moduleForTvSeries() {
    single { TvSeriesDetailRemoteDataSource(get(), get()) }
    single<TvSeriesDetailRepository> { TvSeriesDetailRepositoryImpl(get()) }
}