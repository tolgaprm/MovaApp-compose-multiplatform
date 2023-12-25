package core.data.remote.pagingSource

import core.data.remote.dto.tv.TvSeriesDto
import core.data.remote.mapper.toTvSeries
import core.data.util.paging.BasePagingSource
import core.domain.model.tv.TvSeries

class TvSeriesPagingSource(
    private val fetchTvSeries: suspend (page: Int) -> List<TvSeriesDto>
) : BasePagingSource<TvSeries>() {
    override suspend fun fetchData(page: Int): List<TvSeries> {
        return fetchTvSeries(page).map { it.toTvSeries() }
    }
}