package core.domain.genre.movie

import core.common.Resource
import core.domain.genre.models.Genre

interface MovieGenreRepository {

    suspend fun getMovieGenreList(language: String = "en"): Resource<List<Genre>>
}