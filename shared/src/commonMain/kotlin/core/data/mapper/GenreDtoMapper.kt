package core.data.mapper

import core.data.genre.dto.GenreDto
import core.domain.genre.models.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(id = id, name = name)
}