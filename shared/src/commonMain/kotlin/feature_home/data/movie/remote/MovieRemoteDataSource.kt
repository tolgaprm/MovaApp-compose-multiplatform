package feature_home.data.movie.remote

import core.data.dto.ApiResponse
import core.data.movie.MovieDto
import feature_home.data.service.HomeService

class MovieRemoteDataSource(
    private val homeService: HomeService
) {
    suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto> {
        return homeService.getNowPlayingMovies(language = language, page = page)
    }

    suspend fun getPopularMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto> {
        return homeService.getPopularMovies(language = language, page = page)
    }

    suspend fun getTopRatedMovies(
        language: String,
        page: Int
    ): ApiResponse<MovieDto> {
        return homeService.getTopRatedMovies(language = language, page = page)
    }
}