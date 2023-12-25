package feature_detail.data.remote.dto.credits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsDto(
    @SerialName("cast") val castDto: List<CastDto>,
    @SerialName("crew") val crewDto: List<CrewDto>
)