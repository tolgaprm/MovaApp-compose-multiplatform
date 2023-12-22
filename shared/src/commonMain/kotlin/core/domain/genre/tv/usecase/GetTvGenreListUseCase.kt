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
            when (val result = tvGenreRepository.getTvGenreList(language)) {
                is Resource.Success -> {
                    result.data?.let {
                        emit(Resource.Success(result.data))
                    }
                }

                is Resource.Error -> {
                    emit(Resource.Error(result.error ?: Exception()))
                }
            }
        }
    }
}