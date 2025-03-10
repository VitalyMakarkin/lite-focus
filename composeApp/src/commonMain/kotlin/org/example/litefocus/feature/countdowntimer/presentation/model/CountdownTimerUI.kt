package org.example.litefocus.feature.countdowntimer.presentation.model

import androidx.compose.runtime.Stable

@Stable
data class CountdownTimerUI(
    val remainingSeconds: Int,
    val canBeStarted: Boolean,
    val canBeReset: Boolean,
    val canBePaused: Boolean,
)