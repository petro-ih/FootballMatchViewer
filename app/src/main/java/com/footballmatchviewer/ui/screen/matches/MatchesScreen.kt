package com.footballmatchviewer.ui.screen.matches

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.footballmatchviewer.R
import com.footballmatchviewer.domain.Order
import com.footballmatchviewer.ui.screen.Screen
import com.footballmatchviewer.ui.screen.error.GenericErrorScreen
import com.footballmatchviewer.ui.screen.error.NoInternetConnectionScreen

private const val SHIMMER_ITEMS_COUNT = 10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MatchesScreen(
    navController: NavController,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MatchesTopBar(
                viewModel, navController
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is MatchesUiState.Success,
            is MatchesUiState.Loading -> {
                PullToRefreshBox(
                    isRefreshing = (state as? MatchesUiState.Loading)?.isRefreshing == true,
                    onRefresh = { viewModel.retryLoading(force = true) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesTopBar(
    viewModel: MatchesViewModel,
    navController: NavController,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.screen_matches_title))
        },
        actions = {
            var order = viewModel.order.collectAsState()
            val rotation by animateFloatAsState(if (order.value == Order.ASCENDING) 0f else 180f)
            IconButton(onClick = {
                viewModel.onChangeOrderClick()
            }) {
                Icon(
                    modifier = Modifier.rotate(rotation),
                    imageVector = Icons.AutoMirrored.Filled.Sort,
                    contentDescription = stringResource(R.string.screen_matches_sort)

                )
            }
            IconButton(onClick = {
                navController.navigate(Screen.SettingsScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(R.string.screen_matches_settings)
                )
            }
        }
    )
}

