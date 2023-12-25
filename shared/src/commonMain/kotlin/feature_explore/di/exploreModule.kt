package feature_explore.di

import feature_explore.data.remote.dataSource.ExploreMovieRemoteDataSource
import feature_explore.data.remote.dataSource.ExploreTvSeriesRemoteDataSource
import feature_explore.data.remote.dataSource.MultiSearchRemoteDataSource
import feature_explore.data.remote.service.ExploreService
import feature_explore.data.remote.service.ExploreServiceImpl
import feature_explore.data.repository.ExploreMovieRepositoryImpl
import feature_explore.data.repository.ExploreTvSeriesRepositoryImpl
import feature_explore.data.repository.MultiSearchRepositoryImpl
import feature_explore.domain.repository.ExploreMovieRepository
import feature_explore.domain.repository.ExploreTvSeriesRepository
import feature_explore.domain.repository.MultiSearchRepository
import feature_explore.presentation.ExploreViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val exploreModule = module {
    single<ExploreService> { ExploreServiceImpl(get(), get()) }
    moduleForMultiSearch()
    moduleForTvSeries()
    moduleForMovies()
    factory {
        ExploreViewModel(
            get(), get(), get(),
            get(), get()
        )
    }
}


private fun Module.moduleForMultiSearch() {
    single { MultiSearchRemoteDataSource(get()) }
    factory<MultiSearchRepository> { MultiSearchRepositoryImpl(get()) }
}

private fun Module.moduleForTvSeries() {
    single { ExploreTvSeriesRemoteDataSource(get()) }
    factory<ExploreTvSeriesRepository> { ExploreTvSeriesRepositoryImpl(get()) }
}


private fun Module.moduleForMovies() {
    single { ExploreMovieRemoteDataSource(get()) }
    factory<ExploreMovieRepository> { ExploreMovieRepositoryImpl(get()) }
}