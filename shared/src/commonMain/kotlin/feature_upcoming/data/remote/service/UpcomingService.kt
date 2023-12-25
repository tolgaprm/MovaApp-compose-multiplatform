package feature_upcoming.data.remote.service

import core.data.remote.dto.movie.MovieDto
import feature_upcoming.data.remote.dto.UpComingApiResponse

interface UpcomingService {

    suspend fun getUpcomingMovies(
        page: Int,
        language: String
    ): UpComingApiResponse<MovieDto>

}