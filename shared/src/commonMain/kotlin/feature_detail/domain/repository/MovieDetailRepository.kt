package feature_detail.domain.repository

import androidx.paging.PagingData
import core.common.Constants
import core.common.Resource
import core.domain.model.movie.Movie
import feature_detail.domain.model.movie.MovieDetail
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