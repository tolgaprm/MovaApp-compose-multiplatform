package feature_detail.data.movie.remote.mapper

import core.data.orZero
import core.data.util.MovaUtil
import feature_detail.data.movie.remote.dto.MovieDetailDto
import feature_detail.domain.movie.MovieDetail

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id.orZero(),
        posterPath = posterPath,
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        runtime = MovaUtil.convertRuntimeToInHoursAndMinutes(runtimeInMin = runtime),
        voteAverage = voteAverage.orZero(),
        formattedVoteCount = MovaUtil.formatVoteCount(voteCount = voteCount),
        genresBySeparatedByComma = genres?.map { it.name }?.joinToString(", ")
    )
}