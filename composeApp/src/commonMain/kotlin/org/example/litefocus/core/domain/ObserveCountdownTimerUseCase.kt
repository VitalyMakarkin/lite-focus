package org.example.litefocus.core.domain

import kotlinx.coroutines.flow.Flow
import org.example.litefocus.core.data.CountdownTimerRepository
import org.example.litefocus.core.model.CountdownTimer

class ObserveCountdownTimerUseCase(
    private val repository: CountdownTimerRepository,
) {
    operator fun invoke(): Flow<CountdownTimer> {
        return repository.observeTimer()
    }
}