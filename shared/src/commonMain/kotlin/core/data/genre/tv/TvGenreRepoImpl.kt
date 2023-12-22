package core.data.genre.tv

import core.common.Resource
import core.data.genre.toGenre
import core.data.util.tryResultReturnResource
import core.domain.genre.models.Genre
import core.domain.genre.tv.TvGenreRepository

class TvGenreRepoImpl(
    private val tvGenreRemoteDataSource: TvGenreRemoteDataSource
) : TvGenreRepository {
    override suspend fun getTvGenreList(language: String): Resource<List<Genre>> {
        return tryResultReturnResource {
            tvGenreRemoteDataSource.getTvGenreList(language)
                .genres.map { it.toGenre() }
        }
    }
}