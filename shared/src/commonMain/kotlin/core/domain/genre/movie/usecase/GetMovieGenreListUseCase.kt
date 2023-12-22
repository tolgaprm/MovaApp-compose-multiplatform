package core.domain.genre.movie.usecase

import core.common.Resource
import core.domain.genre.models.Genre
import core.domain.genre.movie.MovieGenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieGenreListUseCase : KoinComponent {
    private val movieGenreRepository: MovieGenreRepository by inject()

    operator fun invoke(language: String = ""): Flow<Resource<List<Genre>>> {
        return flow {
            when (val result = movieGenreRepository.getMovieGenreList(language)) {
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