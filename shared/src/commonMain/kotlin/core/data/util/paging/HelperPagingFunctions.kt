package core.data.util.paging

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import core.common.Constants
import core.data.remote.dto.ApiResponse
import core.data.remote.dto.movie.MovieDto
import core.data.remote.dto.tv.TvSeriesDto
import core.data.remote.pagingSource.MoviePagingSource
import core.data.remote.pagingSource.TvSeriesPagingSource
import core.domain.model.movie.Movie
import core.domain.model.tv.TvSeries
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