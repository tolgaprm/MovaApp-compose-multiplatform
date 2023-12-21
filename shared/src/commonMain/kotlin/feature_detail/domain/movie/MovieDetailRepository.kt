package feature_detail.domain.movie

import core.common.Constants
import core.common.Resource
import feature_detail.domain.movie.model.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(
        id: Int,
        language: String = "en",
        countryIsoCode: String = Constants.DEFAULT_COUNTRY_ISO_CODE
    ): Resource<MovieDetail>
}