package feature_person_detail.domain.model

data class PersonDetail(
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String,
    val deathDay: String?,
    val placeOfBirth: String,
    val profilePath: String?
)