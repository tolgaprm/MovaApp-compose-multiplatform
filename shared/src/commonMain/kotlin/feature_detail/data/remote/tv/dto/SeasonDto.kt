package feature_detail.data.remote.tv.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDto(
    @SerialName("air_date") val airDate: String?,
    @SerialName("episode_count") val episodeCount: Int?,
    val id: Int?,
    val name: String?,
    val overview: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("season_number") val seasonNumber: Int?,
    @SerialName("vote_average") val voteAverage: Double?
)