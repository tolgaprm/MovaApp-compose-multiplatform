package core.data.remote.mapper

import core.data.remote.dto.movie.MovieDto
import core.data.util.MovaUtil
import core.data.util.orZero
import core.domain.model.movie.Movie

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