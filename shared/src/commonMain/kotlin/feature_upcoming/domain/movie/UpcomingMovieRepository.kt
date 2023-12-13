package feature_upcoming.domain.movie

import androidx.paging.PagingData
import core.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface UpcomingMovieRepository {

    fun getUpcomingMovies(
        language: String
    ): Flow<PagingData<Movie>>
}