package core.domain.repository

import core.common.Resource
import core.domain.model.genre.Genre

interface TvGenreRepository {

    suspend fun getTvGenreList(language: String = "en"): Resource<List<Genre>>
}