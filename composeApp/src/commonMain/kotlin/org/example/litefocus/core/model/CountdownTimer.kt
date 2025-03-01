package org.example.litefocus.core.model

data class CountdownTimer(
    val remainingSeconds: Int = 0,
    val state: CountdownTimerState = CountdownTimerState.INITIAL,
) {
    enum class CountdownTimerState { INITIAL, STARTED, PAUSED, FINISHED }

    val canBeStarted
        get() = setOf(CountdownTimerState.INITIAL, CountdownTimerState.PAUSED).contains(state)

    val canBePaused
        get() = state == CountdownTimerState.STARTED

    val canBeReplay
        get() = setOf(CountdownTimerState.PAUSED, CountdownTimerState.FINISHED).contains(state)
}