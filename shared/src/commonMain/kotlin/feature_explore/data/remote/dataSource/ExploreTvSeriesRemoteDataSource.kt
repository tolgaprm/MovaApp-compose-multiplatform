package feature_explore.data.remote.dataSource

import core.data.remote.dto.ApiResponse
import core.data.remote.dto.tv.TvSeriesDto
import feature_explore.data.remote.service.ExploreService

class ExploreTvSeriesRemoteDataSource(
    private val exploreService: ExploreService
) {
    suspend fun discoverTvSeries(
        language: String,
        page: Int,
        withGenres: String = "",
        sortBy: String
    ): ApiResponse<TvSeriesDto> {
        return exploreService.discoverTvSeries(
            language = language,
            page = page,
            withGenres = withGenres,
            sortBy = sortBy
        )
    }
}