package feature_detail.data.remote.dto.credits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrewDto(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    @SerialName("known_for_department") val knownForDepartment: String?,
    val name: String?,
    @SerialName("original_name") val originalName: String?,
    val popularity: Double?,
    @SerialName("profile_path") val profilePath: String?,
    @SerialName("credit_id") val creditId: String?,
    val department: String?,
    val job: String?
)