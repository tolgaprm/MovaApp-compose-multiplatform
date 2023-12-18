package feature_explore.domain.movie

import app.cash.paging.PagingData
import core.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface ExploreMovieRepository {

    fun discoverMovie(
        language: String = "en",
        withGenres: String,
        sortBy: String
    ): Flow<PagingData<Movie>>
}