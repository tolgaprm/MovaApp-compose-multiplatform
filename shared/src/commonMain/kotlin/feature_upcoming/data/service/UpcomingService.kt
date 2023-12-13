package feature_upcoming.data.service

import core.data.movie.MovieDto
import feature_upcoming.data.dto.UpComingApiResponse

interface UpcomingService {

    suspend fun getUpcomingMovies(
        page: Int,
        language: String
    ): UpComingApiResponse<MovieDto>

}