package com.footballmatchviewer.ui.screen.matches

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

private const val SHIMMER_ITEMS_COUNT = 10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MatchesScreen(
    navController: NavController,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()


    when (val state = uiState) {
        is MatchesUiState.Success,
        MatchesUiState.Loading -> {
            PullToRefreshBox(
                isRefreshing = state is MatchesUiState.Loading,
                onRefresh = { viewModel.retryLoading(force = true) },
                content = {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        if (state is MatchesUiState.Success) {
                            items(state.matches.size) { i ->
                                MatchesCard(match = state.matches[i])
                            }
                        } else {
                            items(SHIMMER_ITEMS_COUNT) {
                                MatchesCardShimmer()
                            }
                        }
                    }
                }
            )
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

