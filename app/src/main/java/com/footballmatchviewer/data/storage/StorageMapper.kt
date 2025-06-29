package com.footballmatchviewer.data.storage

import com.footballmatchviewer.data.storage.entity.MatchEntity
import com.footballmatchviewer.data.storage.entity.MatchWithTeams
import com.footballmatchviewer.data.storage.entity.TeamEntity
import com.footballmatchviewer.domain.MatchModel
import java.util.Date
import javax.inject.Inject

class StorageMapper @Inject constructor() {
    fun mapMatchEntityToModel(match: MatchWithTeams): MatchModel {
        return MatchModel(
            id = match.match.id,
            homeTeam = MatchModel.Team(
                id = match.homeTeam.id,
                name = match.homeTeam.name,
                icon = match.homeTeam.icon,
                goals = match.match.homeTeamGoals,
            ),
            awayTeam = MatchModel.Team(
                id = match.awayTeam.id,
                name = match.awayTeam.name,
                icon = match.awayTeam.icon,
                goals = match.match.awayTeamGoals,
            ),
            timeStamp = Date(match.match.date)
        )
    }

    fun mapMatchModelToMatchEntity(match: MatchModel): MatchEntity {
        return MatchEntity(
            id = match.id,
            homeTeamId = match.homeTeam.id,
            awayTeamId = match.awayTeam.id,
            date = match.timeStamp.time,
            homeTeamGoals = match.homeTeam.goals,
            awayTeamGoals = match.awayTeam.goals,
        )
    }

    fun mapTeamModelToTeamEntity(team: MatchModel.Team): TeamEntity {
        return TeamEntity(
            id = team.id,
            name = team.name,
            icon = team.icon,
        )
    }
}