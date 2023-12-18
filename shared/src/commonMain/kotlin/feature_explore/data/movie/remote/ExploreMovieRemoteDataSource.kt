package feature_explore.data.movie.remote

import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import feature_explore.data.remote.service.ExploreService

class ExploreMovieRemoteDataSource(
    private val exploreService: ExploreService
) {

    suspend fun discoverMovie(
        language: String,
        page: Int,
        withGenres: String = "",
        sortBy: String
    ): ApiResponse<MovieDto> {
        return exploreService.discoverMovie(
            language = language,
            page = page,
            withGenres = withGenres,
            sortBy = sortBy
        )
    }
}