package feature_home.domain.movie.usecase

import app.cash.paging.PagingData
import core.common.Resource
import core.domain.genre.movie.usecase.GetMovieGenreListUseCase
import core.domain.movie.Movie
import core.domain.util.combineMovieAndGenreReturnResourceFlow
import feature_home.domain.movie.HomeMovieRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetNowPlayingMoviesUseCase : KoinComponent {
    private val homeMovieRepository: HomeMovieRepository by inject()
    private val getMovieGenreListUseCase: GetMovieGenreListUseCase by inject()

    operator fun invoke(language: String = "en"): Resource<Flow<PagingData<Movie>>> {
        return combineMovieAndGenreReturnResourceFlow(
            movieGenreResourceFlow = getMovieGenreListUseCase(language = language),
            moviePagingDataFlow = homeMovieRepository.getNowPlayingMovies(language = language)
        )
    }
}