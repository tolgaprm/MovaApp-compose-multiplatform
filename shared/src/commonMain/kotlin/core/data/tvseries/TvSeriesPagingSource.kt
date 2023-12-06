package core.data.tvseries

import core.data.util.BasePagingSource
import core.domain.tvseries.TvSeries

class TvSeriesPagingSource(
    private val fetchTvSeries: suspend (page: Int) -> List<TvSeriesDto>
) : BasePagingSource<TvSeries>() {
    override suspend fun fetchData(page: Int): List<TvSeries> {
        return fetchTvSeries(page).map { it.toTvSeries() }
    }
}