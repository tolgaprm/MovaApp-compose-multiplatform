package feature_detail.data.tv

import androidx.paging.PagingData
import core.common.Resource
import core.data.util.tryResultReturnResource
import core.domain.tvseries.TvSeries
import feature_detail.data.tv.remote.TvSeriesDetailRemoteDataSource
import feature_detail.data.tv.remote.mapper.toTvSeriesDetail
import feature_detail.domain.tv.TvSeriesDetailRepository
import feature_detail.domain.tv.model.TvSeriesDetail
import kotlinx.coroutines.flow.Flow

class TvSeriesDetailRepositoryImpl(
    private val tvSeriesDetailRemoteDataSource: TvSeriesDetailRemoteDataSource
) : TvSeriesDetailRepository {
    override suspend fun getTvSeriesDetail(
        id: Int,
        language: String,
        countryIsoCode: String
    ): Resource<TvSeriesDetail> {
        return tryResultReturnResource {
            tvSeriesDetailRemoteDataSource.getTvSeriesDetail(
                id = id,
                language = language
            ).toTvSeriesDetail(countryIsoCode)
        }
    }

    override fun getTvSeriesRecommendations(id: Int, language: String): Flow<PagingData<TvSeries>> {
        return tvSeriesDetailRemoteDataSource.getTvSeriesRecommendations(
            id = id,
            language = language
        )
    }
}