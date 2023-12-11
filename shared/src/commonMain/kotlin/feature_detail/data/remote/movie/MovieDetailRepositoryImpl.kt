package feature_detail.data.remote.movie

import core.common.Resource
import core.data.util.tryResultReturnResource
import feature_detail.data.remote.movie.mapper.toMovieDetail
import feature_detail.domain.movie.MovieDetail
import feature_detail.domain.movie.MovieDetailRepository

class MovieDetailRepositoryImpl(
    private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource
) : MovieDetailRepository {
    override suspend fun getMovieDetail(id: Int, language: String): Resource<MovieDetail> {
        return tryResultReturnResource {
            movieDetailRemoteDataSource.getMovieDetail(id, language)
                .toMovieDetail()
        }
    }
}