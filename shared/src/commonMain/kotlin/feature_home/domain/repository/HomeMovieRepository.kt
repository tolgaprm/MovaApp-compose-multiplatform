package feature_home.domain.repository

import app.cash.paging.PagingData
import core.domain.movie.models.Movie
import kotlinx.coroutines.flow.Flow

interface HomeMovieRepository {

    fun getNowPlayingMovies(
        language: String
    ): Flow<PagingData<Movie>>

    fun getPopularMovies(
        language: String
    ): Flow<PagingData<Movie>>

    fun getTopRatedMovies(
        language: String
    ): Flow<PagingData<Movie>>
}