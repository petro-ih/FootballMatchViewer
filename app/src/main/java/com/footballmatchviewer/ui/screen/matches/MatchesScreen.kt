package com.footballmatchviewer.ui.screen.matches


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun MatchesScreen(
    navController: NavController, viewModel: MatchesViewModel = viewModel()
) {
    val matches by viewModel.fixture.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            if (matches.isEmpty()) {
                items(10) {
                    MatchesCardShimmer()
                }
            } else {
                items(matches.size) { i ->
                    MatchesCard(match = matches[i])
                }
            }
        }
    }
}