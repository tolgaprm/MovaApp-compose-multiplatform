package feature_upcoming.data.movie.remote

import core.data.movie.MovieDto
import feature_upcoming.data.dto.UpComingApiResponse
import feature_upcoming.data.service.UpcomingService

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