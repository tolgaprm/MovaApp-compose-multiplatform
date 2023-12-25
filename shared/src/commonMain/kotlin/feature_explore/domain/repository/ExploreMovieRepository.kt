package feature_explore.domain.repository

import app.cash.paging.PagingData
import core.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface ExploreMovieRepository {

    fun discoverMovie(
        language: String = "en",
        withGenres: String,
        sortBy: String
    ): Flow<PagingData<Movie>>
}