package com.footballmatchviewer.ui.screen.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footballmatchviewer.network.FootballService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MatchesViewModel : ViewModel() {
    val fixture = MutableStateFlow<List<MatchUiItem>>(emptyList())

    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    init {
        viewModelScope.launch {
            try {
                val response =
                    FootballService.api.getFixtures("08b6a7d1817984d57a730a735ed9d98b", 2021, 39)
                fixture.value = response.items.map {
                    MatchUiItem(
                        homeTeam = MatchUiItem.Team(
                            name = it.teams.home.name,
                            icon = it.teams.home.logo,
                            goals = it.goals.home
                        ),
                        awayTeam = MatchUiItem.Team(
                            name = it.teams.away.name,
                            icon = it.teams.away.logo,
                            goals = it.goals.away
                        ),
                        date = dateFormatter.format(it.fixture.timeStamp),
                        time = timeFormatter.format(it.fixture.timeStamp)
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}