package com.footballmatchviewer.ui.screen.matches

import com.footballmatchviewer.domain.MatchModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MatchesUiMapper @Inject constructor() {

    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun mapMatch(match: MatchModel): MatchUiItem {
        return MatchUiItem(
            homeTeam = MatchUiItem.Team(
                name = match.homeTeam.name,
                icon = match.homeTeam.icon,
                goals = match.homeTeam.goals
            ),
            awayTeam = MatchUiItem.Team(
                name = match.awayTeam.name,
                icon = match.awayTeam.icon,
                goals = match.awayTeam.goals
            ),
            date = dateFormatter.format(match.timeStamp),
            time = timeFormatter.format(match.timeStamp)
        )
    }
}