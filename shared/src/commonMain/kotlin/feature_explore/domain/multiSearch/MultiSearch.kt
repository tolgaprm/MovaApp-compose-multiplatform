package feature_explore.domain.multiSearch

import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import feature_explore.domain.model.PersonSearch

sealed interface MultiSearch {
    data class MovieItem(val movie: Movie) : MultiSearch
    data class TvSeriesItem(val tvSeries: TvSeries) : MultiSearch
    data class PersonItem(val person: PersonSearch) : MultiSearch
}