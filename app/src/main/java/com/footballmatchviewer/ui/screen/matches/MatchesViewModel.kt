package com.footballmatchviewer.ui.screen.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footballmatchviewer.data.MatchesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesUiMapper: MatchesUiMapper,
    private val matchesDataSource: MatchesDataSource,
) : ViewModel() {
    private var loadingJob: Job? = null
    val uiState = MutableStateFlow<MatchesUiState>(MatchesUiState.Loading)

    init {
        retryLoading()
    }

    fun retryLoading() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            refresh()
        }
    }

    private suspend fun refresh() {
        try {
            uiState.value = MatchesUiState.Loading
            val matches = matchesDataSource.getMatches(season = 2021, league = 39)
            uiState.value = MatchesUiState.Success(matches.map(matchesUiMapper::mapMatch))
        } catch (e: java.io.IOException) {
            uiState.value = MatchesUiState.NoInternet
        } catch (e: Exception) {
            uiState.value = MatchesUiState.Error(e.localizedMessage ?: "Unknown error")
            e.printStackTrace()
        }
    }
}