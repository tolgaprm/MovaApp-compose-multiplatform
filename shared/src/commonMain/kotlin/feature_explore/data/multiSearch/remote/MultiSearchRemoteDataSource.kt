package feature_explore.data.multiSearch.remote

import core.data.dto.ApiResponse
import feature_explore.data.multiSearch.dto.SearchDto
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