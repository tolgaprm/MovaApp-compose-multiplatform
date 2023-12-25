package core.domain.repository

import core.common.Resource
import core.domain.model.genre.Genre

interface MovieGenreRepository {

    suspend fun getMovieGenreList(language: String = "en"): Resource<List<Genre>>
}