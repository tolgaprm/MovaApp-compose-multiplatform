package feature_detail.data.remote.tv.mapper

import core.data.orZero
import core.data.util.MovaUtil
import feature_detail.data.remote.tv.dto.TvSeriesDetailDto
import feature_detail.domain.tv.model.SeriesStatus
import feature_detail.domain.tv.model.TvSeriesDetail

fun TvSeriesDetailDto.toTvSeriesDetail(): TvSeriesDetail =
    TvSeriesDetail(
        id = id.orZero(),
        posterPath = posterPath,
        releaseDate = MovaUtil.getYearFromReleaseDate(releaseDate = firstAirDate),
        name = name.orEmpty(),
        numberOfSeasons = numberOfSeasons.orZero(),
        status = SeriesStatus.fromValue(status.orEmpty()),
        overview = overview.orEmpty(),
        voteAverage = voteAverage.orZero(),
        formattedVoteCount = MovaUtil.formatVoteCount(voteCount = voteCount)
    )