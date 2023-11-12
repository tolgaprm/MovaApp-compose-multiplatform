package core.domain.genre.tv.repository

import core.domain.genre.models.Genre

interface TvGenreRepository {

    suspend fun getTvGenreList(language: String = "en"): List<Genre>
}