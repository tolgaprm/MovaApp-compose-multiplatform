package feature_detail.domain.tv

import core.common.Constants
import core.common.Resource
import feature_detail.domain.tv.model.TvSeriesDetail

interface TvSeriesDetailRepository {
    suspend fun getTvSeriesDetail(
        id: Int,
        language: String = "en",
        countryIsoCode: String = Constants.DEFAULT_COUNTRY_ISO_CODE
    ): Resource<TvSeriesDetail>
}