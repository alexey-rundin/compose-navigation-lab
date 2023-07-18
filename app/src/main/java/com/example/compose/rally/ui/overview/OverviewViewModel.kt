package com.example.compose.rally.ui.overview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class OverviewUiState(val someParams: String = "")

class OverviewViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(OverviewUiState())

    val uiState: StateFlow<OverviewUiState> = _uiState
}