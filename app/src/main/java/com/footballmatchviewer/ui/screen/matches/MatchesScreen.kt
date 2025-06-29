package com.footballmatchviewer.ui.screen.matches

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
internal fun MatchesScreen(
    navController: NavController,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        MatchesUiState.Loading -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(10) {
                    MatchesCardShimmer()
                }
            }
        }

        is MatchesUiState.Success -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.matches.size) { i ->
                    MatchesCard(match = state.matches[i])
                }
            }
        }

        MatchesUiState.NoInternet -> {
            NoInternetConnectionScreen(
                onRetry = { viewModel.retryLoading() }
            )
        }

        is MatchesUiState.Error -> {
            GenericErrorScreen(
                onRetry = { viewModel.retryLoading() }
            )
        }
    }
}

