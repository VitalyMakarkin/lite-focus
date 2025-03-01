package org.example.litefocus.core.datasource

import kotlinx.coroutines.flow.Flow
import org.example.litefocus.core.model.CountdownTimer

interface CountdownTimerDataSource {
    fun observeTimer(): Flow<CountdownTimer>
    fun setTimer(seconds: Int)
    fun startTimer()
    fun pauseTimer()
    fun stopTimer()
}

