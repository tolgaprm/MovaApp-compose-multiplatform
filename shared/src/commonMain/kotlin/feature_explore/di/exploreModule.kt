package feature_explore.di

import feature_explore.data.movie.ExploreMovieRepositoryImpl
import feature_explore.data.movie.remote.ExploreMovieRemoteDataSource
import feature_explore.data.multiSearch.MultiSearchRepositoryImpl
import feature_explore.data.multiSearch.remote.MultiSearchRemoteDataSource
import feature_explore.data.remote.service.ExploreService
import feature_explore.data.remote.service.ExploreServiceImpl
import feature_explore.data.tv.ExploreTvSeriesRepositoryImpl
import feature_explore.data.tv.remote.ExploreTvSeriesRemoteDataSource
import feature_explore.domain.movie.ExploreMovieRepository
import feature_explore.domain.multiSearch.MultiSearchRepository
import feature_explore.domain.tv.ExploreTvSeriesRepository
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