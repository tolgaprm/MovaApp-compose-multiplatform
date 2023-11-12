package core.data.genre.movie.remote

import core.data.genre.dto.GenreListResponse
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class MovieGenreRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getMovieGenreList(
        language: String
    ): GenreListResponse {
        return tryResult {
            httpClient.get {
                url("genre/movie/list")
                parameter("language", language)
            }.body()
        }
    }
}