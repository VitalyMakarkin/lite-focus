package org.example.litefocus.feature.countdowntimer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.litefocus.feature.countdowntimer.domain.CountdownTimerInteractor

private const val INITIAL_TIMER_VALUE_IN_SECONDS = 1500

class CountdownTimerViewModel(
    private val interactor: CountdownTimerInteractor,
) : ViewModel() {

    private val _countdownTimer = interactor.observeTimer()
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

    init {
        resetTimer()
    }

    fun startTimer() {
        interactor.startTimer()
    }

    fun pauseTimer() {
        interactor.pauseTimer()
    }

    fun resetTimer() {
        interactor.setTimer(seconds = INITIAL_TIMER_VALUE_IN_SECONDS)
    }
}