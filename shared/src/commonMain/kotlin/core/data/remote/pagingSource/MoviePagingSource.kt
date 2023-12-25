package core.data.remote.pagingSource

import core.data.remote.dto.movie.MovieDto
import core.data.remote.mapper.toMovie
import core.data.util.paging.BasePagingSource
import core.domain.model.movie.Movie

class MoviePagingSource(
    private val fetchMovie: suspend (page: Int) -> List<MovieDto>
) : BasePagingSource<Movie>() {
    override suspend fun fetchData(page: Int): List<Movie> {
        return fetchMovie(page).map { it.toMovie() }
    }
}