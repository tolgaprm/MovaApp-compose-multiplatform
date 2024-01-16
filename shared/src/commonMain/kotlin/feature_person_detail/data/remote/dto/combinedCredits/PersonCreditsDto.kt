package feature_person_detail.data.remote.dto.combinedCredits

import kotlinx.serialization.Serializable

@Serializable
data class PersonCreditsDto(
    val cast: List<PersonCastDto>,
    val crew: List<PersonCrewDto>
)