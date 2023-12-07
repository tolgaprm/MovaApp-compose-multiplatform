package core.data.genre.tv

import core.data.genre.toGenre
import core.domain.genre.models.Genre
import core.domain.genre.tv.TvGenreRepository

class TvGenreRepoImpl(
    private val tvGenreRemoteDataSource: TvGenreRemoteDataSource
) : TvGenreRepository {
    override suspend fun getTvGenreList(language: String): List<Genre> {
        return tvGenreRemoteDataSource.getTvGenreList(language)
            .genres.map { it.toGenre() }
    }
}