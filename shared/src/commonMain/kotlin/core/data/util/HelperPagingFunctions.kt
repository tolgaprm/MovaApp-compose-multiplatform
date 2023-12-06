package core.data.util

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import core.common.Constants
import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import core.data.movie.MoviePagingSource
import core.data.tvseries.TvSeriesDto
import core.data.tvseries.TvSeriesPagingSource
import core.domain.movie.Movie
import core.domain.tvseries.TvSeries
import kotlinx.coroutines.flow.Flow

inline fun getPagingTvSeries(
    crossinline tvFetcher: suspend (page: Int) -> ApiResponse<TvSeriesDto>
): Flow<PagingData<TvSeries>> {
    return Pager(
        config = PagingConfig(
            pageSize = Constants.ITEMS_PER_PAGE,
        ),
        pagingSourceFactory = {
            TvSeriesPagingSource(
                fetchTvSeries = { page ->
                    tvFetcher(page).results
                }
            )
        }
    ).flow
}

inline fun getPagingMovies(
    crossinline movieFetcher: suspend (page: Int) -> ApiResponse<MovieDto>
): Flow<PagingData<Movie>> {
    return Pager(
        config = PagingConfig(
            pageSize = Constants.ITEMS_PER_PAGE,
        ),
        pagingSourceFactory = {
            MoviePagingSource(
                fetchMovie = { page ->
                    movieFetcher(page).results
                }
            )
        }
    ).flow
}