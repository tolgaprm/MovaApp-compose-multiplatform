package core.data.genre.service

import core.data.genre.dto.GenreListResponse

interface GenreService {

    suspend fun getMovieGenreList(
        language: String
    ): GenreListResponse

    suspend fun getTvSeriesGenreList(
        language: String
    ): GenreListResponse
}