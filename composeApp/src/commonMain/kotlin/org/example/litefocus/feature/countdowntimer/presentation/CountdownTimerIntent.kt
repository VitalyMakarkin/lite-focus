package org.example.litefocus.feature.countdowntimer.presentation

sealed class CountdownTimerIntent {
    data object Start : CountdownTimerIntent()
    data object Pause : CountdownTimerIntent()
    data object Reset : CountdownTimerIntent()
}