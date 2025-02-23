package org.example.litefocus.core.model

data class CountdownTimer(
    val remainingTimeInMillis: Long,
    val state: CountdownTimerState,
) {
    enum class CountdownTimerState {
        INITIAL,
        STARTED,
        PAUSED,
        FINISHED,
    }

    val canBeStarted
        get() = setOf(CountdownTimerState.INITIAL, CountdownTimerState.PAUSED).contains(state)

    val canBePaused
        get() = state == CountdownTimerState.STARTED

    val canBeReplay
        get() = setOf(CountdownTimerState.PAUSED, CountdownTimerState.FINISHED).contains(state)
}