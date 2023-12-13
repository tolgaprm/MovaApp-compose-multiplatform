package feature_upcoming.domain.movie.usecase

import androidx.paging.PagingData
import core.common.Resource
import core.domain.genre.movie.usecase.GetMovieGenreListUseCase
import core.domain.movie.Movie
import core.domain.util.combineMovieAndGenreReturnResourceFlow
import feature_upcoming.domain.movie.UpcomingMovieRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUpcomingMovieUseCase : KoinComponent {
    private val upcomingMovieRepository: UpcomingMovieRepository by inject()
    private val getMovieGenreListUseCase: GetMovieGenreListUseCase by inject()

    operator fun invoke(
        language: String = "en"
    ): Resource<Flow<PagingData<Movie>>> {
        return combineMovieAndGenreReturnResourceFlow(
            movieGenreResourceFlow = getMovieGenreListUseCase(language = language),
            moviePagingDataFlow = upcomingMovieRepository.getUpcomingMovies(language = language)
        )
    }
}