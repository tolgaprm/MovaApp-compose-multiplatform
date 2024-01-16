package feature_person_detail.domain.model.combinedCredits

import kotlinx.serialization.Serializable

@Serializable
data class PersonCredit(
    val crew: List<PersonCrew>,
    val cast: List<PersonCast>
)