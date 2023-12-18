package feature_explore.domain.tv

import app.cash.paging.PagingData
import core.domain.tvseries.TvSeries
import kotlinx.coroutines.flow.Flow

interface ExploreTvSeriesRepository {

    fun discoverTvSeries(
        language: String = "en",
        withGenres: String,
        sortBy: String
    ): Flow<PagingData<TvSeries>>
}