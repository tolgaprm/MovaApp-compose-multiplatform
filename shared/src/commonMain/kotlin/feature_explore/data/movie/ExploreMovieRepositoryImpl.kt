package feature_explore.data.movie

import app.cash.paging.PagingData
import core.data.util.getPagingMovies
import core.domain.movie.Movie
import feature_explore.data.movie.remote.ExploreMovieRemoteDataSource
import feature_explore.domain.movie.ExploreMovieRepository
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