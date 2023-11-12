package core.data.genre.movie.repository

import core.data.genre.movie.remote.MovieGenreRemoteDataSource
import core.data.mapper.toGenre
import core.domain.genre.models.Genre
import core.domain.genre.movie.repository.MovieGenreRepository

class MovieGenreRepoImpl(
    private val movieGenreRemoteDataSource: MovieGenreRemoteDataSource
) : MovieGenreRepository {
    override suspend fun getMovieGenreList(language: String): List<Genre> {
        return movieGenreRemoteDataSource.getMovieGenreList(language)
            .genres.map { it.toGenre() }
    }
}