package feature_explore.di

import feature_explore.data.multiSearch.MultiSearchRepositoryImpl
import feature_explore.data.multiSearch.remote.MultiSearchRemoteDataSource
import feature_explore.data.remote.service.ExploreService
import feature_explore.data.remote.service.ExploreServiceImpl
import feature_explore.domain.multiSearch.MultiSearchRepository
import feature_explore.presentation.ExploreViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val exploreModule = module {
    single<ExploreService> { ExploreServiceImpl(get(), get()) }
    moduleForMultiSearch()
    factory { ExploreViewModel(get()) }
}


private fun Module.moduleForMultiSearch() {
    single { MultiSearchRemoteDataSource(get()) }
    factory<MultiSearchRepository> { MultiSearchRepositoryImpl(get()) }
}