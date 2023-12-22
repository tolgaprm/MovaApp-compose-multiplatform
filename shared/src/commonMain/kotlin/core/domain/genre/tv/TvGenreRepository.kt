package core.domain.genre.tv

import core.common.Resource
import core.domain.genre.models.Genre

interface TvGenreRepository {

    suspend fun getTvGenreList(language: String = "en"): Resource<List<Genre>>
}