package feature_person_detail.domain.model.combinedCredits

data class PersonCredit(
    val crew: List<PersonCrew>,
    val cast: List<PersonCast>
)