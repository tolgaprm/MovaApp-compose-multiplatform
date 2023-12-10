package feature_detail.data.remote.tv.dto

import kotlinx.serialization.Serializable

@Serializable
data class LastEpisodeToAirDto(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val airDate: String?,
    val episodeNumber: Int?,
    val productionCode: String?,
    val runtime: Int?,
    val seasonNumber: Int?,
    val showId: Int?,
    val stillPath: String?,
)