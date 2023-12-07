package core.data.genre

import core.data.genre.dto.GenreDto
import core.data.orZero
import core.domain.genre.models.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id.orZero(),
        name = name.orEmpty()
    )
}