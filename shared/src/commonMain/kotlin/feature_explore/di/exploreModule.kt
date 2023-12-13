package feature_explore.di

import feature_explore.presentation.ExploreViewModel
import org.koin.dsl.module

val exploreModule = module {
    factory { ExploreViewModel() }
}