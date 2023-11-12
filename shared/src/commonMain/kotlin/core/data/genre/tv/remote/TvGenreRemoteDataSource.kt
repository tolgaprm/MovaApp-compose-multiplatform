package core.data.genre.tv.remote

import core.data.genre.dto.GenreListResponse
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class TvGenreRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getTvGenreList(
        language: String
    ): GenreListResponse {
        return tryResult {
            httpClient.get {
                url("genre/tv/list")
                parameter("language", language)
            }
        }
    }
}