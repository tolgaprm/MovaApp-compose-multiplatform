package feature_explore.data.tv.remote

import core.data.dto.ApiResponse
import core.data.tvseries.TvSeriesDto
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