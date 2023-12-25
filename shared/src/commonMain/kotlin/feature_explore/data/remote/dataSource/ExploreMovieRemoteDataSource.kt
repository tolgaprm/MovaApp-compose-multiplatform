package feature_explore.data.remote.dataSource

import core.data.remote.dto.ApiResponse
import core.data.remote.dto.movie.MovieDto
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