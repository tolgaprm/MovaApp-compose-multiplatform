package feature_explore.data.multiSearch

import core.data.util.BasePagingSource
import feature_explore.data.multiSearch.mapper.toMovie
import feature_explore.data.multiSearch.mapper.toPersonSearch
import feature_explore.data.multiSearch.mapper.toTvSeries
import feature_explore.data.multiSearch.remote.MultiSearchRemoteDataSource
import feature_explore.domain.model.MediaType
import feature_explore.domain.multiSearch.MultiSearch

class MultiSearchPagingSource(
    private val remoteDataSource: MultiSearchRemoteDataSource,
    private val query: String,
    private val language: String
) : BasePagingSource<MultiSearch>() {
    override suspend fun fetchData(page: Int): List<MultiSearch> {
        val response = remoteDataSource.multiSearch(
            query = query,
            language = language,
            page = page
        ).results

        return response.map { searchDto ->
            when (searchDto.mediaType) {
                MediaType.MOVIE.value -> {
                    val movie = searchDto.toMovie()
                    MultiSearch.MovieItem(movie)
                }

                MediaType.TV_SERIES.value -> {
                    val tv = searchDto.toTvSeries()
                    MultiSearch.TvSeriesItem(tv)
                }

                MediaType.PERSON.value -> {
                    val person = searchDto.toPersonSearch()
                    MultiSearch.PersonItem(person)
                }

                else -> {
                    throw IllegalArgumentException("Unknown media type")
                }
            }
        }
    }
}