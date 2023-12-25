package feature_explore.data.remote.mapper

import core.data.util.MovaUtil
import core.data.util.orZero
import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries
import feature_explore.data.remote.dto.multiSearch.SearchDto
import feature_explore.domain.model.PersonSearch

fun SearchDto.toMovie(): Movie {
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

fun SearchDto.toTvSeries(): TvSeries {
    return TvSeries(
        id = id.orZero(),
        genreIds = genreIds.orEmpty(),
        name = name.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath,
        releaseDate = MovaUtil.getYearFromReleaseDate(firstAirDate),
        voteAverage = voteAverage.orZero(),
        formattedVoteCount = MovaUtil.formatVoteCount(voteCount)
    )
}

fun SearchDto.toPersonSearch(): PersonSearch {
    return PersonSearch(
        id = id.orZero(),
        name = name.orEmpty(),
        personImageUrl = profilePath,
        knownForDepartment = knownForDepartment.orEmpty()
    )
}