package feature_detail.data.movie.remote.mapper

import core.data.orZero
import core.data.util.MovaUtil
import feature_detail.data.mapper.toDirectors
import feature_detail.data.mapper.toListCast
import feature_detail.data.mapper.toWatchProviderItem
import feature_detail.data.movie.remote.dto.MovieDetailDto
import feature_detail.domain.movie.model.MovieDetail

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