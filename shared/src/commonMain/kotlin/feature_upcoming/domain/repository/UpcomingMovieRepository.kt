package feature_upcoming.domain.repository

import androidx.paging.PagingData
import core.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface UpcomingMovieRepository {

    fun getUpcomingMovies(
        language: String
    ): Flow<PagingData<Movie>>
}