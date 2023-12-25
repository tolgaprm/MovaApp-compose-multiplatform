package feature_explore.domain.repository

import app.cash.paging.PagingData
import core.domain.model.tv.TvSeries
import kotlinx.coroutines.flow.Flow

interface ExploreTvSeriesRepository {

    fun discoverTvSeries(
        language: String = "en",
        withGenres: String,
        sortBy: String
    ): Flow<PagingData<TvSeries>>
}