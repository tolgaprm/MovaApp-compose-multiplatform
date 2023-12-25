package core.data.remote.service.genre

import core.data.remote.dto.genre.GenreListResponse

interface GenreService {

    suspend fun getMovieGenreList(
        language: String
    ): GenreListResponse

    suspend fun getTvSeriesGenreList(
        language: String
    ): GenreListResponse
}