package core.data.genre.tv.repository

import core.data.genre.tv.remote.TvGenreRemoteDataSource
import core.data.mapper.toGenre
import core.domain.genre.models.Genre
import core.domain.genre.tv.repository.TvGenreRepository

class TvGenreRepoImpl(
    private val tvGenreRemoteDataSource: TvGenreRemoteDataSource
) : TvGenreRepository {
    override suspend fun getTvGenreList(language: String): List<Genre> {
        return tvGenreRemoteDataSource.getTvGenreList(language)
            .genres.map { it.toGenre() }
    }
}