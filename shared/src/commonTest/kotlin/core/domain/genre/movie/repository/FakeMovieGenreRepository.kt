package core.domain.genre.movie.repository

import core.domain.genre.CoreTestConstants
import core.domain.genre.models.Genre
import core.domain.genre.movie.MovieGenreRepository

class FakeMovieGenreRepository : MovieGenreRepository {
    override suspend fun getMovieGenreList(language: String): List<Genre> {
        return if (language == "en") {
            CoreTestConstants.movieEnGenreList
        } else {
            emptyList()
        }
    }
}