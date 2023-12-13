package feature_detail.data.movie.remote

import feature_detail.data.movie.remote.dto.MovieDetailDto
import feature_detail.data.service.DetailService

class MovieDetailRemoteDataSource(
    private val detailService: DetailService
) {

    suspend fun getMovieDetail(
        id: Int,
        language: String
    ): MovieDetailDto {
        return detailService.getMovieDetail(id = id, language = language)
    }
}