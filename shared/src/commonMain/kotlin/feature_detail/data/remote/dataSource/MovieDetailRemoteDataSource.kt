package feature_detail.data.remote.dataSource

import androidx.paging.PagingData
import core.data.util.paging.getPagingMovies
import core.domain.model.movie.Movie
import feature_detail.data.remote.dto.movie.MovieDetailDto
import feature_detail.data.remote.service.DetailService
import kotlinx.coroutines.flow.Flow

class MovieDetailRemoteDataSource(
    private val detailService: DetailService
) {

    suspend fun getMovieDetail(
        id: Int,
        language: String
    ): MovieDetailDto {
        return detailService.getMovieDetail(movieId = id, language = language)
    }

    fun getMovieRecommendations(
        id: Int,
        language: String
    ): Flow<PagingData<Movie>> {
        return getPagingMovies { page ->
            detailService.getMovieRecommendations(movieId = id, language = language, page = page)
        }
    }
}