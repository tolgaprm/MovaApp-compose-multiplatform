package core.data.remote.dataSources.genre.movie

import core.common.Resource
import core.data.remote.mapper.toGenre
import core.data.util.tryResultReturnResource
import core.domain.model.genre.Genre
import core.domain.repository.MovieGenreRepository

class MovieGenreRepoImpl(
    private val movieGenreRemoteDataSource: MovieGenreRemoteDataSource
) : MovieGenreRepository {
    override suspend fun getMovieGenreList(language: String): Resource<List<Genre>> {
        return tryResultReturnResource {
            movieGenreRemoteDataSource.getMovieGenreList(language).genres.map { it.toGenre() }
        }
    }
}