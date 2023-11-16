package core.domain.genre.movie.repository

import core.domain.genre.models.Genre

interface MovieGenreRepository {

    suspend fun getMovieGenreList(language: String): List<Genre>
}