package feature_detail.data.remote.mapper.movie

import core.data.util.MovaUtil
import core.data.util.orZero
import feature_detail.data.remote.dto.movie.MovieDetailDto
import feature_detail.data.remote.mapper.credit.toDirectors
import feature_detail.data.remote.mapper.credit.toListCast
import feature_detail.data.remote.mapper.watchProvider.toWatchProviderItem
import feature_detail.domain.model.movie.MovieDetail

fun MovieDetailDto.toMovieDetail(
    countryIsoCode: String
): MovieDetail {
    return MovieDetail(
        id = id.orZero(),
        posterPath = posterPath,
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        runtime = MovaUtil.convertRuntimeToInHoursAndMinutes(runtimeInMin = runtime),
        voteAverage = voteAverage.orZero(),
        formattedVoteCount = MovaUtil.formatVoteCount(voteCount = voteCount),
        genresBySeparatedByComma = genres?.map { it.name }?.joinToString(", "),
        casts = credits?.castDto?.toListCast().orEmpty(),
        directors = credits?.crewDto.toDirectors(),
        watchProviderItem = watchProviders?.results?.toWatchProviderItem(countryIsoCode = countryIsoCode)
    )
}