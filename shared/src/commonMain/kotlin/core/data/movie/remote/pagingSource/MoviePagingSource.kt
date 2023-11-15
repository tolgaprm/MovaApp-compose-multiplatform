package core.data.movie.remote.pagingSource

import core.data.mapper.toMovie
import core.data.movie.dto.MovieDto
import core.data.util.BasePagingSource
import core.domain.movie.models.Movie

class MoviePagingSource(
    private val fetchMovie: suspend (page: Int) -> List<MovieDto>
) : BasePagingSource<Movie>() {
    override suspend fun fetchData(page: Int): List<Movie> {
        return fetchMovie(page).map { it.toMovie() }
    }
}