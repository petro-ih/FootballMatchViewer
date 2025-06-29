package com.footballmatchviewer.data

import com.footballmatchviewer.data.network.NetworkModule
import com.footballmatchviewer.data.storage.DatabaseModule
import com.footballmatchviewer.domain.MatchModel
import javax.inject.Inject

class MatchesRepository @Inject internal constructor(
    @NetworkModule.Network
    val networkDataSource: MatchesDataSource,
    @DatabaseModule.Database
    val storageDataSource: MatchesDataSource,
) : MatchesDataSource {

    override suspend fun getMatches(
        league: Int,
        season: Int,
        forceReload: Boolean
    ): List<MatchModel> {
        if (!forceReload) {
            val matchesInCache = storageDataSource.getMatches(league, season)
            if (matchesInCache.isNotEmpty()) {
                return matchesInCache
            }
        }

        val loadedMatches = networkDataSource.getMatches(league, season)
        storageDataSource.saveMatches(loadedMatches)

        return loadedMatches
    }
}