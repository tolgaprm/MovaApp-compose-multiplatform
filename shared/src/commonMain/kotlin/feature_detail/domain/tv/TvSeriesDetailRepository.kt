package feature_detail.domain.tv

import androidx.paging.PagingData
import core.common.Constants
import core.common.Resource
import core.domain.tvseries.TvSeries
import feature_detail.domain.tv.model.TvSeriesDetail
import kotlinx.coroutines.flow.Flow

interface TvSeriesDetailRepository {
    suspend fun getTvSeriesDetail(
        id: Int,
        language: String = "en",
        countryIsoCode: String = Constants.DEFAULT_COUNTRY_ISO_CODE
    ): Resource<TvSeriesDetail>

    fun getTvSeriesRecommendations(
        id: Int,
        language: String = "en"
    ): Flow<PagingData<TvSeries>>
}