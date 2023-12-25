package feature_explore.domain.model

import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries

sealed interface MultiSearch {
    data class MovieItem(val movie: Movie) : MultiSearch
    data class TvSeriesItem(val tvSeries: TvSeries) : MultiSearch
    data class PersonItem(val person: PersonSearch) : MultiSearch
}