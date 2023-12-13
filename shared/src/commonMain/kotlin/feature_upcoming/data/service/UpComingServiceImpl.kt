package feature_upcoming.data.service

import core.common.dispatcher.DispatcherProvider
import core.data.addCommonParameters
import core.data.movie.MovieDto
import core.data.util.tryResult
import feature_upcoming.data.dto.UpComingApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class UpComingServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider
) : UpcomingService {
    override suspend fun getUpcomingMovies(
        page: Int,
        language: String
    ): UpComingApiResponse<MovieDto> {
        return withContext(dispatcherProvider.IO) {
            tryResult {
                httpClient.get(UPCOMING_MOVIES_URL) {
                    addCommonParameters(
                        language = language,
                        page = page
                    )
                }
            }
        }
    }

    companion object {
        private const val UPCOMING_MOVIES_URL = "movie/upcoming"
    }
}