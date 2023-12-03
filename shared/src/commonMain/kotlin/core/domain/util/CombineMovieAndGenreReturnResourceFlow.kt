package core.domain.util

import app.cash.paging.PagingData
import app.cash.paging.map
import core.common.Resource
import core.domain.genre.models.Genre
import core.domain.movie.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

fun combineMovieAndGenreReturnResourceFlow(
    movieGenreResourceFlow: Flow<Resource<List<Genre>>>,
    moviePagingDataFlow: Flow<PagingData<Movie>>
): Resource<Flow<PagingData<Movie>>> {
    return try {
        val result = combine(
            moviePagingDataFlow,
            movieGenreResourceFlow
        ) { nowPlayingPagingData, movieGenreList ->
            movieGenreList.data?.let {
                nowPlayingPagingData.map { movie ->
                    movie.copy(
                        genresBySeparatedByComma = GenreUtil.getGenresBySeparatedByComma(
                            movie.genreIds,
                            it
                        ),
                    )
                }
            } ?: nowPlayingPagingData
        }
        Resource.Success(data = result)
    } catch (e: Exception) {
        Resource.Error(e)
    }
}