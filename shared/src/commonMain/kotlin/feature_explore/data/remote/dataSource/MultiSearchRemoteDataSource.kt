package feature_explore.data.remote.dataSource

import core.data.remote.dto.ApiResponse
import feature_explore.data.remote.dto.multiSearch.SearchDto
import feature_explore.data.remote.service.ExploreService

class MultiSearchRemoteDataSource(
    private val exploreService: ExploreService
) {

    suspend fun multiSearch(
        query: String,
        language: String,
        page: Int
    ): ApiResponse<SearchDto> {
        return exploreService.multiSearch(query, language, page)
    }
}