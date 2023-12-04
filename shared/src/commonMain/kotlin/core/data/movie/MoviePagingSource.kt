package core.data.movie

import core.data.mapper.toMovie
import core.data.util.BasePagingSource
import core.domain.movie.Movie

class MoviePagingSource(
    private val fetchMovie: suspend (page: Int) -> List<MovieDto>
) : BasePagingSource<Movie>() {
    override suspend fun fetchData(page: Int): List<Movie> {
        return fetchMovie(page).map { it.toMovie() }
    }
}