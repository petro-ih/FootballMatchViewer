package com.footballmatchviewer.ui.screen.matches

sealed class MatchesUiState {
    data object Loading : MatchesUiState()
    data class Success(val matches: List<MatchUiItem>) : MatchesUiState()
    data object NoInternet : MatchesUiState()
    data class Error(val message: String) : MatchesUiState()
}