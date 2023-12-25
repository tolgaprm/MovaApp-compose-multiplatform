package feature_home.domain.repository

import app.cash.paging.PagingData
import core.domain.model.tv.TvSeries
import kotlinx.coroutines.flow.Flow

interface HomeTvSeriesRepository {
    fun getPopularTvSeries(
        language: String
    ): Flow<PagingData<TvSeries>>

    fun getTopRatedTvSeries(
        language: String
    ): Flow<PagingData<TvSeries>>
}