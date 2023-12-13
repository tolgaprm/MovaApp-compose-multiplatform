package core.di

import feature_detail.di.detailModule
import feature_explore.di.exploreModule
import feature_home.di.homeModule
import feature_upcoming.di.upcomingModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            platformModule(),
            coreModule,
            homeModule,
            detailModule,
            exploreModule,
            upcomingModule,
        )
    }
}