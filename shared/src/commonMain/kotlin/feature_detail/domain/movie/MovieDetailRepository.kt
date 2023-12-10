package feature_detail.domain.movie

import core.common.Resource

interface MovieDetailRepository {
    suspend fun getMovieDetail(id: Int, language: String): Resource<MovieDetail>
}