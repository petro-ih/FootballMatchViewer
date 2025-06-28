package com.footballmatchviewer.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FootballApi {
    @GET("fixtures")
    suspend fun getFixtures(
        @Header("x-apisports-key") apiKey: String,
        @Query("season") season: Int,
        @Query("league") league: Int
    ): FixtureResponse
}