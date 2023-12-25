package feature_detail.data.remote.mapper.tv

import core.data.util.MovaUtil
import core.data.util.orFalse
import core.data.util.orZero
import feature_detail.data.remote.dto.tv.TvSeriesDetailDto
import feature_detail.data.remote.mapper.credit.toListCast
import feature_detail.data.remote.mapper.watchProvider.toWatchProviderItem
import feature_detail.domain.model.tv.TvSeriesDetail

fun TvSeriesDetailDto.toTvSeriesDetail(
    countryIsoCode: String
): TvSeriesDetail = TvSeriesDetail(
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
    genresBySeparatedByComma = genres?.map { it.name }?.joinToString(", "),
    cast = credits?.castDto?.toListCast().orEmpty(),
    directors = createdByDto?.take(2)?.map { it.toDirector() }.orEmpty(),
    watchProviderItem = watchProviders?.results?.toWatchProviderItem(countryIsoCode = countryIsoCode)
)