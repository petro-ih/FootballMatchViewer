package com.footballmatchviewer.ui.screen.matches

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footballmatchviewer.domain.MatchesUseCase
import com.footballmatchviewer.domain.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesUiMapper: MatchesUiMapper,
    private val matchesUseCase: MatchesUseCase,
) : ViewModel() {
    private var loadingJob: Job? = null

    val order = MutableStateFlow(Order.ASCENDING)
    val uiState = MutableStateFlow<MatchesUiState>(MatchesUiState.Loading(isRefreshing = false))

    init {
        retryLoading()
    }

    fun retryLoading(force: Boolean = false) {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            refresh(force)
        }
    }

    fun onChangeOrderClick() {
        order.value = order.value.inversed()
        retryLoading()
    }

    private suspend fun refresh(force: Boolean) {
        try {
            uiState.value = MatchesUiState.Loading(isRefreshing = force)
            delay(1_000) // Simulate long server response
            val matches = matchesUseCase
                .getMatches(
                    order = order.value,
                    forceReload = force
                )
            uiState.value = MatchesUiState.Success(matches.map(matchesUiMapper::mapMatch))
        } catch (e: java.io.IOException) {
            uiState.value = MatchesUiState.NoInternet
        } catch (e: Exception) {
            uiState.value = MatchesUiState.Error(e.localizedMessage ?: "Unknown error")
            Log.e("MatchesViewModel", "Unable to load matches", e)
        }
    }
}