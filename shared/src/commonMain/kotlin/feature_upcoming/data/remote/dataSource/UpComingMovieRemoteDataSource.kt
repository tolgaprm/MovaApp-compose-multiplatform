package feature_upcoming.data.remote.dataSource

import core.data.remote.dto.movie.MovieDto
import feature_upcoming.data.remote.dto.UpComingApiResponse
import feature_upcoming.data.remote.service.UpcomingService

class UpComingMovieRemoteDataSource(
    private val upcomingService: UpcomingService
) {

    suspend fun getUpComingMovies(
        page: Int,
        language: String
    ): UpComingApiResponse<MovieDto> {
        return upcomingService.getUpcomingMovies(
            page = page,
            language = language
        )
    }
}