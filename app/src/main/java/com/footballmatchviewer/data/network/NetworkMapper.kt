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
        id = response.fixture.id,
        homeTeam = MatchModel.Team(
            id = response.teams.home.id,
            name = response.teams.home.name,
            icon = response.teams.home.logo,
            goals = response.goals?.home
        ),
        awayTeam = MatchModel.Team(
            id = response.teams.away.id,
            name = response.teams.away.name,
            icon = response.teams.away.logo,
            goals = response.goals?.away
        ),
        timeStamp = Date(TimeUnit.SECONDS.toMillis(response.fixture.timeStampSeconds))
    )
}