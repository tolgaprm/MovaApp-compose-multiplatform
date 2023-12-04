package core.di

import core.common.dispatcher.createDefaultDispatcher
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { OkHttp.create() }
    single { createDefaultDispatcher() }
}