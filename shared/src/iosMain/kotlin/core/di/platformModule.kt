package core.di

import core.common.dispatcher.createDefaultDispatcher
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { Darwin.create() }
    single { createDefaultDispatcher() }
}