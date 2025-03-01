package org.example.litefocus.core.data

import kotlinx.coroutines.flow.Flow
import org.example.litefocus.core.model.CountdownTimer

interface CountdownTimerRepository {
    fun observeTimer(): Flow<CountdownTimer>
    fun setTimer(seconds: Int)
    fun startTimer()
    fun pauseTimer()
    fun stopTimer()
}

