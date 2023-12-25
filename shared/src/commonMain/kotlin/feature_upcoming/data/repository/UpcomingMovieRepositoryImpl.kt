package feature_upcoming.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import app.cash.paging.PagingConfig
import core.common.Constants
import core.data.remote.pagingSource.MoviePagingSource
import core.domain.model.movie.Movie
import feature_upcoming.data.remote.dataSource.UpComingMovieRemoteDataSource
import feature_upcoming.domain.repository.UpcomingMovieRepository
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