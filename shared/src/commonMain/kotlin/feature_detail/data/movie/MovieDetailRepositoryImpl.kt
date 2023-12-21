package feature_detail.data.movie

import androidx.paging.PagingData
import core.common.Resource
import core.data.util.tryResultReturnResource
import core.domain.movie.Movie
import feature_detail.data.movie.remote.MovieDetailRemoteDataSource
import feature_detail.data.movie.remote.mapper.toMovieDetail
import feature_detail.domain.movie.MovieDetailRepository
import feature_detail.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow

class MovieDetailRepositoryImpl(
    private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource
) : MovieDetailRepository {
    override suspend fun getMovieDetail(
        id: Int,
        language: String,
        countryIsoCode: String
    ): Resource<MovieDetail> {
        return tryResultReturnResource {
            movieDetailRemoteDataSource.getMovieDetail(id, language)
                .toMovieDetail(
                    countryIsoCode = countryIsoCode
                )
        }
    }

    override fun getMovieRecommendations(id: Int, language: String): Flow<PagingData<Movie>> {
        return movieDetailRemoteDataSource.getMovieRecommendations(
            id = id,
            language = language
        )
    }
}