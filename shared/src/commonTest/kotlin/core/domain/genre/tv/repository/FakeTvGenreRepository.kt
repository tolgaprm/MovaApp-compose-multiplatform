package core.domain.genre.tv.repository

import core.domain.genre.CoreTestConstants
import core.domain.genre.models.Genre

class FakeTvGenreRepository : TvGenreRepository {
    override suspend fun getTvGenreList(language: String): List<Genre> {
        return if (language == "en") {
            CoreTestConstants.tvGenreList
        } else {
            emptyList()
        }
    }
}