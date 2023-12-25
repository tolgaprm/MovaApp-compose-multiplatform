package core.data.remote.mapper

import core.data.remote.dto.tv.TvSeriesDto
import core.data.util.MovaUtil
import core.data.util.orZero
import core.domain.model.tv.TvSeries

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