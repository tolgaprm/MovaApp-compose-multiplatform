package feature_upcoming.data.movie

import androidx.paging.Pager
import androidx.paging.PagingData
import app.cash.paging.PagingConfig
import core.common.Constants
import core.data.movie.MoviePagingSource
import core.domain.movie.Movie
import feature_upcoming.data.movie.remote.UpComingMovieRemoteDataSource
import feature_upcoming.domain.movie.UpcomingMovieRepository
import kotlinx.coroutines.flow.Flow

class UpcomingMovieRepositoryImpl(
    private val upComingMovieRemoteDataSource: UpComingMovieRemoteDataSource
) : UpcomingMovieRepository {
    override fun getUpcomingMovies(language: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ITEMS_PER_PAGE,
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    fetchMovie = { page ->
                        upComingMovieRemoteDataSource.getUpComingMovies(
                            language = language,
                            page = page
                        ).results
                    }
                )
            }
        ).flow
    }
}