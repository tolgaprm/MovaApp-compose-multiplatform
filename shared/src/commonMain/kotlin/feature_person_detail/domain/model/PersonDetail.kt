package feature_person_detail.domain.model

import feature_person_detail.domain.model.combinedCredits.PersonCredit

data class PersonDetail(
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String?,
    val deathDay: String?,
    val placeOfBirth: String,
    val profilePath: String?,
    val combinedCredit: PersonCredit?
)