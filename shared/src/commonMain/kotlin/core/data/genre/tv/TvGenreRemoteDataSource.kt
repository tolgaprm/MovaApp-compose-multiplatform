package core.data.genre.tv

import core.data.genre.dto.GenreListResponse
import core.data.genre.service.GenreService

class TvGenreRemoteDataSource(
    private val genreService: GenreService
) {
    suspend fun getTvGenreList(
        language: String
    ): GenreListResponse {
        return genreService.getTvSeriesGenreList(language)
    }
}