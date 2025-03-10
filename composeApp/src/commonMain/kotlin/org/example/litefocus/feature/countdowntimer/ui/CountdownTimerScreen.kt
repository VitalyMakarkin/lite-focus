package org.example.litefocus.feature.countdowntimer.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.litefocus.feature.countdowntimer.presentation.CountdownTimerIntent
import org.example.litefocus.feature.countdowntimer.presentation.CountdownTimerUiState
import org.example.litefocus.feature.countdowntimer.presentation.CountdownTimerViewModel
import org.example.litefocus.feature.countdowntimer.presentation.model.CountdownTimerUI
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CountdownTimerScreen(
    modifier: Modifier = Modifier,
    viewModel: CountdownTimerViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    CountdownTimerScreen(
        uiState = uiState,
        onStartTimerClick = { viewModel.sendIntent(CountdownTimerIntent.Start) },
        onPauseTimerClick = { viewModel.sendIntent(CountdownTimerIntent.Pause) },
        onResetTimerClick = { viewModel.sendIntent(CountdownTimerIntent.Reset) },
        modifier = modifier,
    )
}

@Composable
private fun CountdownTimerScreen(
    uiState: CountdownTimerUiState,
    onStartTimerClick: () -> Unit,
    onPauseTimerClick: () -> Unit,
    onResetTimerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    uiState.countdownTimer?.let { timer ->
        Scaffold(modifier = modifier) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TimerText(remainingSeconds = timer.remainingSeconds)
                Spacer(modifier = Modifier.height(64.dp))
                TimerButtonGroup(
                    timer = timer,
                    onStart = onStartTimerClick,
                    onPause = onPauseTimerClick,
                    onReset = onResetTimerClick,
                )
            }
        }
    }
}

@Composable
private fun TimerText(
    remainingSeconds: Int,
    modifier: Modifier = Modifier,
) {
    val timeText = remember(remainingSeconds) {
        convertToCountdownTime(remainingSeconds)
    }
    Text(
        text = timeText,
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        fontSize = 64.sp,
    )
}

private fun convertToCountdownTime(seconds: Int) = "%02d:%02d".format(seconds / 60, seconds % 60)

@Composable
private fun TimerButtonGroup(
    timer: CountdownTimerUI,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .animateContentSize()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TimerButton(
            onClick = onStart,
            imageVector = Icons.Default.PlayArrow,
            visible = timer.canBeStarted,
        )
        TimerButton(
            onClick = onPause,
            imageVector = Icons.Default.Replay,
            visible = timer.canBeReset,
        )
        TimerButton(
            onClick = onReset,
            imageVector = Icons.Default.Pause,
            visible = timer.canBePaused,
        )
    }
}

@Composable
private fun TimerButton(
    onClick: () -> Unit,
    visible: Boolean,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(),
        exit = ExitTransition.None,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier,
            contentPadding = PaddingValues(all = 32.dp),
            shape = RoundedCornerShape(percent = 100),
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
            )
        }
    }
}