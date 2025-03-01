package org.example.litefocus.core.data

import kotlinx.coroutines.flow.Flow
import org.example.litefocus.core.datasource.CountdownTimerDataSource
import org.example.litefocus.core.model.CountdownTimer

class DefaultCountdownTimerRepository(
    private val datasource: CountdownTimerDataSource,
) : CountdownTimerRepository {

    override fun observeTimer(): Flow<CountdownTimer> {
        return datasource.observeTimer()
    }

    override fun setTimer(seconds: Int) {
        datasource.setTimer(seconds)
    }

    override fun startTimer() {
        datasource.startTimer()
    }

    override fun stopTimer() {
        datasource.stopTimer()
    }

    override fun pauseTimer() {
        datasource.pauseTimer()
    }
}