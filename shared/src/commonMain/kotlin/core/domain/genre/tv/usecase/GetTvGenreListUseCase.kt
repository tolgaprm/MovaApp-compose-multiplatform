package core.domain.genre.tv.usecase

import core.common.Resource
import core.domain.genre.models.Genre
import core.domain.genre.tv.TvGenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetTvGenreListUseCase : KoinComponent {
    private val tvGenreRepository: TvGenreRepository by inject()

    operator fun invoke(language: String = ""): Flow<Resource<List<Genre>>> {
        return flow {
            try {
                val result = tvGenreRepository.getTvGenreList(language)
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}