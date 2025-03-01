package org.example.litefocus.core.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.example.litefocus.core.model.CountdownTimer

class DefaultCountdownTimerDataSource(
    private val applicationScope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher,
) : CountdownTimerDataSource {

    private var timerJob: Job? = null
    private val timer: MutableStateFlow<CountdownTimer> = MutableStateFlow(CountdownTimer())

    override fun observeTimer(): Flow<CountdownTimer> = timer

    override fun setTimer(seconds: Int) {
        cancelTimerJob()
        timer.update { it.copy(remainingSeconds = seconds, state = CountdownTimer.CountdownTimerState.INITIAL) }
    }

    override fun startTimer() {
        if (timerJob?.isActive == true) timerJob?.cancel()
        timer.update { it.copy(state = CountdownTimer.CountdownTimerState.STARTED) }
        timerJob = applicationScope.launch(ioDispatcher) {
            while (isActive) {
                delay(1000L)
                val remainingSeconds = timer.value.remainingSeconds
                if (remainingSeconds > 0) {
                    timer.update { it.copy(remainingSeconds = remainingSeconds - 1) }
                } else {
                    stopTimer()
                    break
                }
            }
        }
    }

    override fun stopTimer() {
        cancelTimerJob()
        timer.update { it.copy(remainingSeconds = 0, state = CountdownTimer.CountdownTimerState.FINISHED) }
    }

    override fun pauseTimer() {
        cancelTimerJob()
        timer.update { it.copy(state = CountdownTimer.CountdownTimerState.PAUSED) }
    }

    private fun cancelTimerJob() {
        timerJob?.cancel()
        timerJob = null
    }
}