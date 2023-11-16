package core.domain.genre.movie.usecase

import core.common.Resource
import core.domain.genre.models.Genre
import core.domain.genre.movie.repository.MovieGenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieGenreListUseCase : KoinComponent {
    private val movieGenreRepository: MovieGenreRepository by inject()

    operator fun invoke(language: String = ""): Flow<Resource<List<Genre>>> {
        return flow {
            try {
                val result = movieGenreRepository.getMovieGenreList(language)
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}