package feature_detail.data.mapper

import core.data.orZero
import feature_detail.data.dto.credits.CastDto
import feature_detail.data.dto.credits.CreditsDto
import feature_detail.data.dto.credits.CrewDto
import feature_detail.domain.model.credits.Cast
import feature_detail.domain.model.credits.Credit
import feature_detail.domain.model.credits.Crew

fun CreditsDto?.toCredit(): Credit {
    return Credit(
        cast = this?.castDto?.toListCast().orEmpty(),
        crew = this?.crewDto?.toListCrew().orEmpty()
    )
}

fun List<CastDto>.toListCast(): List<Cast> {
    return map {
        Cast(
            id = it.id.orZero(),
            name = it.name.orEmpty(),
            originalName = it.originalName.orEmpty(),
            profilePath = it.profilePath,
            character = it.character.orEmpty()
        )
    }
}

fun List<CrewDto>.toListCrew(): List<Crew> {
    return map {
        Crew(
            id = it.id.orZero(),
            name = it.name.orEmpty(),
            originalName = it.originalName.orEmpty(),
            profilePath = it.profilePath,
            department = it.department.orEmpty()
        )
    }
}

