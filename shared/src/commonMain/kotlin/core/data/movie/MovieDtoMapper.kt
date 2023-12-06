package core.data.movie

import core.data.orZero
import core.domain.movie.Movie
import core.domain.util.MovaUtil

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id.orZero(),
        genreIds = genreIds.orEmpty(),
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath,
        releaseDate = MovaUtil.getYearFromReleaseDate(releaseDate),
        voteAverage = voteAverage.orZero(),
        formattedVoteCount = MovaUtil.formatVoteCount(voteCount)
    )
}