package org.example.litefocus.feature.countdowntimer.presentation

import org.example.litefocus.core.model.CountdownTimer

data class CountdownTimerUiState(
    val countdownTimer: CountdownTimer? = null,
    val isLoading: Boolean = false,
)