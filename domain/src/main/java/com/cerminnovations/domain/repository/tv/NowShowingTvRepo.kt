package com.cerminnovations.domain.repository.tv

import androidx.paging.PagingData
import com.cerminnovations.domain.model.series.TvSeries
import kotlinx.coroutines.flow.Flow

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface NowShowingTvRepo {
    fun getNowShowingSeries(): Flow<PagingData<TvSeries>>
}
