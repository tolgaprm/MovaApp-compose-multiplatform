package core.di

import feature_home.di.homeModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(platformModule(), coreModule, homeModule)
    }
}
