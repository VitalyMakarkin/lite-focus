package org.example.litefocus

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.example.litefocus.feature.countdowntimer.ui.CountdownTimerScreen
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            CountdownTimerScreen()
        }
    }
}