package core.di

import core.common.TMDBConstants
import core.data.genre.movie.MovieGenreRemoteDataSource
import core.data.genre.movie.MovieGenreRepoImpl
import core.data.genre.tv.TvGenreRemoteDataSource
import core.data.genre.tv.TvGenreRepoImpl
import core.domain.genre.movie.usecase.GetMovieGenreListUseCase
import core.domain.genre.tv.usecase.GetTvGenreListUseCase
import feature_splash.presentation.SplashViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import main.MainViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val coreModule = module {
    single { createHttpClient(get()) }
    moduleForMovies()
    moduleForTvSeries()

    factory { MainViewModel() }
    factory { SplashViewModel() }
}

private fun createHttpClient(
    httpClientEngine: HttpClientEngine
) = HttpClient(httpClientEngine) {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }

    install(HttpCache) {
    }

    defaultRequest {
        url {
            takeFrom(TMDBConstants.TMDB_BASE_URL)
            parameters.append("api_key", TMDBConstants.API_KEY)
        }
    }
}

private fun Module.moduleForMovies() {
    single { MovieGenreRemoteDataSource(get()) }
    single { MovieGenreRepoImpl(get()) }
    factory { GetMovieGenreListUseCase() }
}

private fun Module.moduleForTvSeries() {
    single { TvGenreRemoteDataSource(get()) }
    single { TvGenreRepoImpl(get()) }
    factory { GetTvGenreListUseCase() }
}