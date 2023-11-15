package core.data.mapper

import core.data.movie.dto.MovieDto
import core.domain.movie.models.Movie

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        genreIds = genreIds,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}