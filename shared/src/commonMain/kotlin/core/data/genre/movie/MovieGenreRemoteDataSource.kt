package core.data.genre.movie

import core.data.genre.dto.GenreListResponse
import core.data.genre.service.GenreService

class MovieGenreRemoteDataSource(
    private val genreService: GenreService
) {
    suspend fun getMovieGenreList(
        language: String
    ): GenreListResponse {
        return genreService.getMovieGenreList(language)
    }
}