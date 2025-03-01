package org.example.litefocus.feature.countdowntimer.domain

import kotlinx.coroutines.flow.Flow
import org.example.litefocus.core.data.CountdownTimerRepository
import org.example.litefocus.core.domain.ObserveCountdownTimerUseCase
import org.example.litefocus.core.model.CountdownTimer

class CountdownTimerInteractor(
    private val repository: CountdownTimerRepository,
    private val observeFormattedCountdownTimer: ObserveCountdownTimerUseCase,
) {
    fun observeTimer(): Flow<CountdownTimer> {
        return observeFormattedCountdownTimer()
    }

    fun setTimer(seconds: Int) {
        repository.setTimer(seconds)
    }

    fun startTimer() {
        repository.startTimer()
    }

    fun pauseTimer() {
        repository.pauseTimer()
    }

    fun stopTimer() {
        repository.stopTimer()
    }
}