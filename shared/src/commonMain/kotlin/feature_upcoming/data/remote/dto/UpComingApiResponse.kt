package feature_upcoming.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpComingApiResponse<T>(
    val dates: Dates,
    val page: Int,
    val results: List<T>,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("total_pages") val totalPages: Int
)

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)