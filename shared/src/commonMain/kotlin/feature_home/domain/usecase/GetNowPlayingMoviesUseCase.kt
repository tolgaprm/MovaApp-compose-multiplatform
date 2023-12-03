package feature_home.domain.usecase

import app.cash.paging.PagingData
import app.cash.paging.map
import core.common.Resource
import core.domain.genre.movie.usecase.GetMovieGenreListUseCase
import core.domain.movie.models.Movie
import core.domain.util.GenreUtil
import feature_home.domain.repository.HomeMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetNowPlayingMoviesUseCase : KoinComponent {
    private val homeMovieRepository: HomeMovieRepository by inject()
    private val getMovieGenreListUseCase: GetMovieGenreListUseCase by inject()

    operator fun invoke(language: String = "en"): Resource<Flow<PagingData<Movie>>> {
        return try {
            val result = combine(
                homeMovieRepository.getNowPlayingMovies(),
                getMovieGenreListUseCase(language = language)
            ) { nowPlayingPagingData, movieGenreList ->
                movieGenreList.data?.let {
                    nowPlayingPagingData.map { movie ->
                        movie.copy(
                            genresBySeparatedByComma = GenreUtil.getGenresBySeparatedByComma(
                                movie.genreIds,
                                it
                            )
                        )
                    }
                } ?: nowPlayingPagingData
            }
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}