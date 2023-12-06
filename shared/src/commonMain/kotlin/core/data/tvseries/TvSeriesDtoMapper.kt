package core.data.tvseries

import core.data.orZero
import core.domain.tvseries.TvSeries
import core.domain.util.MovaUtil

fun TvSeriesDto.toTvSeries(): TvSeries {
    return TvSeries(
        id = id.orZero(),
        name = name.orEmpty(),
        posterPath = posterPath,
        overview = overview.orEmpty(),
        releaseDate = MovaUtil.getYearFromReleaseDate(releaseDate = firstAirDate),
        genreIds = genreIds.orEmpty(),
        voteAverage = voteAverage.orZero(),
        formattedVoteCount = MovaUtil.formatVoteCount(voteCount)
    )
}