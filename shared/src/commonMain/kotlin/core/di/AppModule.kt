package core.di

import feature_detail.di.detailModule
import feature_home.di.homeModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            platformModule(),
            coreModule,
            homeModule,
            detailModule
        )
    }
}
