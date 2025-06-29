package com.footballmatchviewer.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = false)
data class FixtureResponse(
    @Json(name = "response")
    val items : List<Response>
) {

    @JsonClass(generateAdapter = false)
    data class Response(
        @Json(name = "fixture")
        val fixture: Fixture,
        @Json(name = "teams")
        val teams: Teams,
        @Json(name = "goals")
        val goals: Goals
    )

    @JsonClass(generateAdapter = false)
    data class Fixture(
        @Json(name = "referee")
        val referee: String,
        @Json(name = "timestamp")
        val timeStampSeconds: Long,
        @Json(name = "status")
        val status: Status
    ) {
        val timeStamp = Date(timeStampSeconds * 1000)
    }

    @JsonClass(generateAdapter = false)
    data class Status(
        @Json(name = "long")
        val long: String,
        @Json(name = "short")
        val short: String,
    )

    @JsonClass(generateAdapter = false)
    data class Teams(
        @Json(name = "home")
        val home: Team,
        @Json(name = "away")
        val away: Team
    )

    @JsonClass(generateAdapter = false)
    data class Team(
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "logo")
        val logo: String,
        @Json(name = "winner")
        val winner: Boolean?
    )

    @JsonClass(generateAdapter = false)
    data class Goals(
        @Json(name = "home")
        val home: Int,
        @Json(name = "away")
        val away: Int
    )
}