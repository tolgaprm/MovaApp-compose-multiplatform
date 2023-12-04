package core.domain.genre.movie

import core.domain.genre.models.Genre

interface MovieGenreRepository {

    suspend fun getMovieGenreList(language: String): List<Genre>
}