package core.domain.genre.tv.usecase

import core.common.Resource
import core.domain.genre.models.Genre
import core.domain.genre.tv.TvGenreRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetTvGenreListUseCase : KoinComponent {
    private val tvGenreRepository: TvGenreRepository by inject()

    suspend operator fun invoke(language: String = ""): Resource<List<Genre>> {
        return try {
            val result = tvGenreRepository.getTvGenreList(language)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}