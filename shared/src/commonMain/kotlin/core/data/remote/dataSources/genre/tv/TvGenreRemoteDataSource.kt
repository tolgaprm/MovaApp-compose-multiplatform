package core.data.remote.dataSources.genre.tv

import core.data.remote.dto.genre.GenreListResponse
import core.data.remote.service.genre.GenreService

class TvGenreRemoteDataSource(
    private val genreService: GenreService
) {
    suspend fun getTvGenreList(
        language: String
    ): GenreListResponse {
        return genreService.getTvSeriesGenreList(language)
    }
}