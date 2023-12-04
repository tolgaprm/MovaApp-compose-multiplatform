package feature_home.data.movie.repository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import core.common.Constants.ITEMS_PER_PAGE
import core.data.movie.dto.MovieDto
import core.data.movie.remote.pagingSource.MoviePagingSource
import core.domain.movie.models.Movie
import feature_home.data.movie.remote.MovieRemoteDataSource
import feature_home.domain.repository.HomeMovieRepository
import kotlinx.coroutines.flow.Flow

class HomeMovieRepoImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : HomeMovieRepository {
    override fun getNowPlayingMovies(language: String): Flow<PagingData<Movie>> {
        return getMovies { page ->
            movieRemoteDataSource.getNowPlayingMovies(
                language = language,
                page = page
            ).results
        }
    }

    override fun getPopularMovies(language: String): Flow<PagingData<Movie>> {
        return getMovies { page ->
            movieRemoteDataSource.getPopularMovies(
                language = language,
                page = page
            ).results
        }
    }

    override fun getTopRatedMovies(language: String): Flow<PagingData<Movie>> {
        return getMovies { page ->
            movieRemoteDataSource.getTopRatedMovies(
                language = language,
                page = page
            ).results
        }
    }
}

private inline fun getMovies(
    crossinline movieFetcher: suspend (page: Int) -> List<MovieDto>
): Flow<PagingData<Movie>> {
    return Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
        ),
        pagingSourceFactory = {
            MoviePagingSource(
                fetchMovie = { page ->
                    movieFetcher(page)
                }
            )
        }
    ).flow
}