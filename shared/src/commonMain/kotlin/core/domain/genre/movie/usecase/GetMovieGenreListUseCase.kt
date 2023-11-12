package core.domain.genre.movie.usecase

import core.common.Resource
import core.domain.genre.models.Genre
import core.domain.genre.movie.repository.MovieGenreRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieGenreListUseCase : KoinComponent {
    private val movieGenreRepository: MovieGenreRepository by inject()

    suspend operator fun invoke(language: String = ""): Resource<List<Genre>> {
        return try {
            val result = movieGenreRepository.getMovieGenreList(language)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}