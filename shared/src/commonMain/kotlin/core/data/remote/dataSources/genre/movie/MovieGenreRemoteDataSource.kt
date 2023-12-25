package core.data.remote.dataSources.genre.movie

import core.data.remote.dto.genre.GenreListResponse
import core.data.remote.service.genre.GenreService

class MovieGenreRemoteDataSource(
    private val genreService: GenreService
) {
    suspend fun getMovieGenreList(
        language: String
    ): GenreListResponse {
        return genreService.getMovieGenreList(language)
    }
}