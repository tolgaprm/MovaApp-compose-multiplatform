package core.data.remote.mapper

import core.data.remote.dto.genre.GenreDto
import core.data.util.orZero
import core.domain.model.genre.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id.orZero(),
        name = name.orEmpty()
    )
}