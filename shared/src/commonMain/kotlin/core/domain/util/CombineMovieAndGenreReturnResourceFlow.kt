package core.domain.util

import app.cash.paging.PagingData
import app.cash.paging.map
import core.common.Resource
import core.domain.genre.models.Genre
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
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
        ) { moviePagingData, movieGenreList ->
            movieGenreList.data?.let {
                moviePagingData.map { movie ->
                    movie.copy(
                        genresBySeparatedByComma = MovaUtil.getGenresBySeparatedByComma(
                            movie.genreIds,
                            it
                        ),
                    )
                }
            } ?: moviePagingData
        }
        Resource.Success(data = result)
    } catch (e: Exception) {
        Resource.Error(e)
    }
}

fun combineTvSeriesAndGenreReturnResourceFlow(
    tvGenreResourceFlow: Flow<Resource<List<Genre>>>,
    tvPagingDataFlow: Flow<PagingData<TvSeries>>
): Resource<Flow<PagingData<TvSeries>>> {
    return try {
        val result = combine(
            tvPagingDataFlow,
            tvGenreResourceFlow
        ) { tvPagingData, tvGenreList ->
            tvGenreList.data?.let {
                tvPagingData.map { tvSeries ->
                    tvSeries.copy(
                        genresBySeparatedByComma = MovaUtil.getGenresBySeparatedByComma(
                            tvSeries.genreIds,
                            it
                        ),
                    )
                }
            } ?: tvPagingData
        }
        Resource.Success(data = result)
    } catch (e: Exception) {
        Resource.Error(e)
    }
}