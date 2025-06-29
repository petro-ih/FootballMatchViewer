package com.footballmatchviewer.data.network

import com.footballmatchviewer.data.network.dto.FixtureResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {
    @GET("fixtures")
    suspend fun getFixtures(
        @Query("season") season: Int,
        @Query("league") league: Int
    ): FixtureResponse
}