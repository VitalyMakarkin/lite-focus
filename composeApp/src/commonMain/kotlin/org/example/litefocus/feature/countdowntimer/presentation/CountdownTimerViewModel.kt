package org.example.litefocus.feature.countdowntimer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.litefocus.feature.countdowntimer.domain.CountdownTimerInteractor
import org.example.litefocus.feature.countdowntimer.presentation.model.CountdownTimerUI

private const val INITIAL_TIMER_VALUE_IN_SECONDS = 1500

class CountdownTimerViewModel(
    private val interactor: CountdownTimerInteractor,
) : ViewModel() {

    private val _countdownTimer = interactor.observeTimer()
    val uiState: StateFlow<CountdownTimerUiState> = _countdownTimer
        .map {
            CountdownTimerUiState(
                countdownTimer = it.let {
                    CountdownTimerUI(
                        remainingSeconds = it.remainingSeconds,
                        canBeStarted = it.canBeStarted,
                        canBePaused = it.canBePaused,
                        canBeReset = it.canBeReset,
                    )
                }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CountdownTimerUiState(),
        )

    init {
        resetTimer()
    }

    fun sendIntent(intent: CountdownTimerIntent) {
        when (intent) {
            CountdownTimerIntent.Start -> startTimer()
            CountdownTimerIntent.Pause -> pauseTimer()
            CountdownTimerIntent.Reset -> resetTimer()
        }
    }

    private fun startTimer() {
        interactor.startTimer()
    }

    private fun pauseTimer() {
        interactor.pauseTimer()
    }

    private fun resetTimer() {
        interactor.setTimer(seconds = INITIAL_TIMER_VALUE_IN_SECONDS)
    }
}