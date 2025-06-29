package com.footballmatchviewer.data.network

import com.footballmatchviewer.data.network.dto.FixtureResponse
import com.footballmatchviewer.data.network.dto.FixtureResponse.Response
import com.footballmatchviewer.domain.MatchModel
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class NetworkMapper @Inject constructor() {
    fun mapFromNetwork(fixtureResponse: FixtureResponse): List<MatchModel> {
        return fixtureResponse.items.map(::mapMatchItem)
    }

    private fun mapMatchItem(response: Response) = MatchModel(
        homeTeam = MatchModel.Team(
            name = response.teams.home.name,
            icon = response.teams.home.logo,
            goals = response.goals.home
        ),
        awayTeam = MatchModel.Team(
            name = response.teams.away.name,
            icon = response.teams.away.logo,
            goals = response.goals.away
        ),
        timeStamp = Date(TimeUnit.SECONDS.toMillis(response.fixture.timeStampSeconds))
    )
}