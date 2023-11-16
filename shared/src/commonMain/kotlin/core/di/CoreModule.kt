package core.di

import core.common.TMDBConstants
import core.data.genre.movie.remote.MovieGenreRemoteDataSource
import core.data.genre.movie.repository.MovieGenreRepoImpl
import core.data.genre.tv.remote.TvGenreRemoteDataSource
import core.data.genre.tv.repository.TvGenreRepoImpl
import core.domain.genre.movie.repository.MovieGenreRepository
import core.domain.genre.movie.usecase.GetMovieGenreListUseCase
import core.domain.genre.tv.repository.TvGenreRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val coreModule = module {
    single { createHttpClient(get()) }
    single { MovieGenreRemoteDataSource(get()) }
    single { TvGenreRemoteDataSource(get()) }
    single<MovieGenreRepository> { MovieGenreRepoImpl(get()) }
    single<TvGenreRepository> { TvGenreRepoImpl(get()) }
    factory { GetMovieGenreListUseCase() }
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

    defaultRequest {
        url {
            takeFrom(TMDBConstants.TMDB_BASE_URL)
            parameters.append("api_key", TMDBConstants.API_KEY)
        }
    }
}