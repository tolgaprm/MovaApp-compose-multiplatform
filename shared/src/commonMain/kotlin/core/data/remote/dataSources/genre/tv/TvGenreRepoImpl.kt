package core.data.remote.dataSources.genre.tv

import core.common.Resource
import core.data.remote.mapper.toGenre
import core.data.util.tryResultReturnResource
import core.domain.model.genre.Genre
import core.domain.repository.TvGenreRepository

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