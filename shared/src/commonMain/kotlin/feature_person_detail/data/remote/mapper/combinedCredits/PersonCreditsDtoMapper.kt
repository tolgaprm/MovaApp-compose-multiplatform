package feature_person_detail.data.remote.mapper.combinedCredits

import feature_person_detail.data.remote.dto.combinedCredits.PersonCreditsDto
import feature_person_detail.domain.model.combinedCredits.PersonCredit

fun PersonCreditsDto.toPersonCredits(): PersonCredit {
    return PersonCredit(
        cast = cast.map { it.toPersonCast() },
        crew = crew.map { it.toPersonCrew() }
    )
}