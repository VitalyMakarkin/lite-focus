package org.example.litefocus.feature.countdowntimer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.example.litefocus.core.model.CountdownTimer
import kotlin.math.max

class CountdownTimerViewModel : ViewModel() {

    private val _countdownTimer: MutableStateFlow<CountdownTimer> = MutableStateFlow(
        CountdownTimer(
            remainingTimeInMillis = 1_500_000L,
            state = CountdownTimer.CountdownTimerState.INITIAL,
        )
    )
    val uiState: StateFlow<CountdownTimerUiState> = _countdownTimer
        .map {
            CountdownTimerUiState(
                countdownTimer = it,
                isLoading = false,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CountdownTimerUiState(isLoading = true),
        )

    private var _timerJob: Job? = null

    fun startTimer() {
        if (_timerJob?.isActive == true) return
        _timerJob = viewModelScope.launch {
            _countdownTimer.update { it.copy(state = CountdownTimer.CountdownTimerState.STARTED) }
            while (isActive) {
                delay(1000L)
                _countdownTimer.update {
                    val newTimeInMillis = max(0, it.remainingTimeInMillis - 1000L)
                    it.copy(remainingTimeInMillis = newTimeInMillis)
                }
                if (_countdownTimer.value.remainingTimeInMillis <= 0) {
                    stopTimer()
                    break
                }
            }
        }
    }

    fun stopTimer() {
        viewModelScope.launch {
            _countdownTimer.update { it.copy(state = CountdownTimer.CountdownTimerState.PAUSED) }
        }
        _timerJob?.cancel()
        _timerJob = null
    }
}