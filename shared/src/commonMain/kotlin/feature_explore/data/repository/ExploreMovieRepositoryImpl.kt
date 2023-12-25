package feature_explore.data.repository

import app.cash.paging.PagingData
import core.data.util.paging.getPagingMovies
import core.domain.model.movie.Movie
import feature_explore.data.remote.dataSource.ExploreMovieRemoteDataSource
import feature_explore.domain.repository.ExploreMovieRepository
import kotlinx.coroutines.flow.Flow

class ExploreMovieRepositoryImpl(
    private val exploreMovieRemoteDataSource: ExploreMovieRemoteDataSource
) : ExploreMovieRepository {
    override fun discoverMovie(
        language: String,
        withGenres: String,
        sortBy: String
    ): Flow<PagingData<Movie>> {
        return getPagingMovies { page ->
            exploreMovieRemoteDataSource.discoverMovie(
                language = language,
                page = page,
                withGenres = withGenres,
                sortBy = sortBy
            )
        }
    }
}