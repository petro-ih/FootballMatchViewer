package com.footballmatchviewer.domain

import com.footballmatchviewer.data.MatchesDataSource
import com.footballmatchviewer.domain.Order.ASCENDING
import javax.inject.Inject

class MatchesUseCase @Inject constructor(
    private val matchesDataSource: MatchesDataSource,
) {
    suspend fun getMatches(
        order: Order,
        forceReload: Boolean,
    ): List<MatchModel> {
        return matchesDataSource
            .getMatches(
                season = 2021,
                league = 39,
                forceReload = forceReload
            )
            .sortedBy { it.timeStamp.time * if (order == ASCENDING) 1 else -1 }
    }
}

enum class Order {
    ASCENDING,
    DESCENDING;

    fun inversed() = if (this == ASCENDING) DESCENDING else ASCENDING
}