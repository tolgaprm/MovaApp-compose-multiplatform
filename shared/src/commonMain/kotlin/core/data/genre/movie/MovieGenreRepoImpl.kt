package core.data.genre.movie

import core.common.Resource
import core.data.genre.toGenre
import core.data.util.tryResultReturnResource
import core.domain.genre.models.Genre
import core.domain.genre.movie.MovieGenreRepository

class MovieGenreRepoImpl(
    private val movieGenreRemoteDataSource: MovieGenreRemoteDataSource
) : MovieGenreRepository {
    override suspend fun getMovieGenreList(language: String): Resource<List<Genre>> {
        return tryResultReturnResource {
            movieGenreRemoteDataSource.getMovieGenreList(language).genres.map { it.toGenre() }
        }
    }
}