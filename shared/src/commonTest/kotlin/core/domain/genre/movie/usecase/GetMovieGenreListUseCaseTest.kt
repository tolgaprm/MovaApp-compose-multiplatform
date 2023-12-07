package core.domain.genre.movie.usecase

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import core.common.Resource
import core.domain.genre.CoreTestConstants
import core.domain.genre.movie.repository.FakeMovieGenreRepository
import core.domain.genre.movie.MovieGenreRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetMovieGenreListUseCaseTest : KoinTest {

    private lateinit var getMovieGenreListUseCase: GetMovieGenreListUseCase

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                module {
                    factory<MovieGenreRepository> { FakeMovieGenreRepository() }
                }
            )
        }
        getMovieGenreListUseCase = GetMovieGenreListUseCase()
    }

    @Test
    fun getMovieGenres() = runBlocking {
        getMovieGenreListUseCase("en").test {
            val result = awaitItem()
            assertThat(result).isInstanceOf(Resource.Success::class)
            assertThat(result.data).isEqualTo(CoreTestConstants.movieEnGenreList)
            cancelAndConsumeRemainingEvents()
        }
    }
}