package feature_detail.domain.movie

import androidx.paging.PagingData
import core.common.Constants
import core.common.Resource
import core.domain.movie.Movie
import feature_detail.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetail(
        id: Int,
        language: String = "en",
        countryIsoCode: String = Constants.DEFAULT_COUNTRY_ISO_CODE
    ): Resource<MovieDetail>

    fun getMovieRecommendations(
        id: Int,
        language: String = "en"
    ): Flow<PagingData<Movie>>
}