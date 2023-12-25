package feature_detail.data.repository

import androidx.paging.PagingData
import core.common.Resource
import core.data.util.tryResultReturnResource
import core.domain.model.movie.Movie
import feature_detail.data.remote.dataSource.MovieDetailRemoteDataSource
import feature_detail.data.remote.mapper.movie.toMovieDetail
import feature_detail.domain.model.movie.MovieDetail
import feature_detail.domain.repository.MovieDetailRepository
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