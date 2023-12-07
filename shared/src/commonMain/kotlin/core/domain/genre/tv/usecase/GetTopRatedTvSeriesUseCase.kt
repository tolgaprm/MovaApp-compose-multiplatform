package core.domain.genre.tv.usecase

import app.cash.paging.PagingData
import core.common.Resource
import core.domain.tvseries.TvSeries
import core.domain.util.combineTvSeriesAndGenreReturnResourceFlow
import feature_home.domain.tv.HomeTvSeriesRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetTopRatedTvSeriesUseCase : KoinComponent {
    private val repository: HomeTvSeriesRepository by inject()
    private val getTvGenreListUseCase: GetTvGenreListUseCase by inject()

    operator fun invoke(language: String = "en"): Resource<Flow<PagingData<TvSeries>>> {
        return combineTvSeriesAndGenreReturnResourceFlow(
            tvPagingDataFlow = repository.getTopRatedTvSeries(language = language),
            tvGenreResourceFlow = getTvGenreListUseCase()
        )
    }
}