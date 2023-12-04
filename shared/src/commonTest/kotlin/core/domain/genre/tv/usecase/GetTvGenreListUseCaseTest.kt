package core.domain.genre.tv.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import core.common.Resource
import core.domain.genre.CoreTestConstants
import core.domain.genre.tv.repository.FakeTvGenreRepository
import core.domain.genre.tv.TvGenreRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetTvGenreListUseCaseTest : KoinTest {

    private lateinit var getTvGenreListUseCase: GetTvGenreListUseCase

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                module {
                    factory<TvGenreRepository> { FakeTvGenreRepository() }
                }
            )
        }
        getTvGenreListUseCase = GetTvGenreListUseCase()
    }

    @Test
    fun getTvGenres() = runBlocking {
        val result = getTvGenreListUseCase("en")
        assertThat(result).isInstanceOf(Resource.Success::class)
        assertThat(result.data).isEqualTo(CoreTestConstants.tvGenreList)
    }
}