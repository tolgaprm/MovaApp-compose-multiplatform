package feature_detail.data.dto.credits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    @SerialName("known_for_department") val knownForDepartment: String?,
    val name: String?,
    @SerialName("original_name") val originalName: String?,
    val popularity: Double?,
    @SerialName("profile_path") val profilePath: String?,
    val character: String?,
    @SerialName("credit_id") val creditId: String?,
    val order: Int?
)