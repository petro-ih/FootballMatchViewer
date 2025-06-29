package com.footballmatchviewer.ui.screen.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footballmatchviewer.data.MatchesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesUiMapper: MatchesUiMapper,
    private val matchesDataSource: MatchesDataSource,
) : ViewModel() {
    val fixture = MutableStateFlow<List<MatchUiItem>>(emptyList())

    init {
        viewModelScope.launch {
            try {
                val matches = matchesDataSource.getMatches(season = 2021, league = 39)
                fixture.value = matches.map(matchesUiMapper::mapMatch)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}