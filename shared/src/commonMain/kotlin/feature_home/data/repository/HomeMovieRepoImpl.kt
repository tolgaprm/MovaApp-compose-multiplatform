package feature_home.data.repository

import app.cash.paging.PagingData
import core.data.util.paging.getPagingMovies
import core.domain.model.movie.Movie
import feature_home.data.remote.dataSource.MovieRemoteDataSource
import feature_home.domain.repository.HomeMovieRepository
import kotlinx.coroutines.flow.Flow

class HomeMovieRepoImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : HomeMovieRepository {
    override fun getNowPlayingMovies(language: String): Flow<PagingData<Movie>> {
        return getPagingMovies { page ->
            movieRemoteDataSource.getNowPlayingMovies(
                language = language,
                page = page
            )
        }
    }

    override fun getPopularMovies(language: String): Flow<PagingData<Movie>> {
        return getPagingMovies { page ->
            movieRemoteDataSource.getPopularMovies(
                language = language,
                page = page
            )
        }
    }

    override fun getTopRatedMovies(language: String): Flow<PagingData<Movie>> {
        return getPagingMovies { page ->
            movieRemoteDataSource.getTopRatedMovies(
                language = language,
                page = page
            )
        }
    }
}