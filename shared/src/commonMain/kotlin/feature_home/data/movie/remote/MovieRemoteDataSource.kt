package feature_home.data.movie.remote

import core.data.dto.ApiResponse
import core.data.movie.dto.MovieDto
import core.data.util.tryResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getNowPlayingMovies(
        language: String = "en",
        page: Int
    ): ApiResponse<MovieDto> {
        return tryResult<ApiResponse<MovieDto>> {
            httpClient.get("movie/now_playing") {
                parameter("language", language)
                parameter("page", page)
            }
        }
    }
}