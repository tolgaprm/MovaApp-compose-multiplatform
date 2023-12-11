package feature_detail.data.remote.tv.mapper

import core.data.orFalse
import core.data.orZero
import core.data.util.MovaUtil
import feature_detail.data.remote.tv.dto.TvSeriesDetailDto
import feature_detail.domain.tv.model.TvSeriesDetail

fun TvSeriesDetailDto.toTvSeriesDetail(): TvSeriesDetail = TvSeriesDetail(
    id = id.orZero(),
    posterPath = posterPath,
    releaseDate = MovaUtil.getYearFromReleaseDate(releaseDate = firstAirDate),
    name = name.orEmpty(),
    numberOfSeasons = numberOfSeasons.orZero(),
    inProduction = inProduction.orFalse(),
    lastAirDate = MovaUtil.getYearFromReleaseDate(lastAirDate),
    overview = overview.orEmpty(),
    voteAverage = voteAverage.orZero(),
    formattedVoteCount = MovaUtil.formatVoteCount(voteCount = voteCount),
    genresBySeparatedByComma = genres?.map { it.name }?.joinToString(", ")
)