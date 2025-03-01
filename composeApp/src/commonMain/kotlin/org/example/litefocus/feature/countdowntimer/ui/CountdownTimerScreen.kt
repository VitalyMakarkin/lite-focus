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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.litefocus.feature.countdowntimer.presentation.CountdownTimerUiState
import org.example.litefocus.feature.countdowntimer.presentation.CountdownTimerViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CountdownTimerScreen(
    modifier: Modifier = Modifier,
    viewModel: CountdownTimerViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    CountdownTimerScreen(
        uiState = uiState,
        onStartTimerClick = viewModel::startTimer,
        onPauseTimerClick = viewModel::pauseTimer,
        onReplayTimerClick = viewModel::resetTimer,
        modifier = modifier,
    )
}

@Composable
private fun CountdownTimerScreen(
    uiState: CountdownTimerUiState,
    onStartTimerClick: () -> Unit,
    onPauseTimerClick: () -> Unit,
    onReplayTimerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (uiState.countdownTimer != null) {
        val startTimerButtonVisibility by remember(uiState.countdownTimer.canBeStarted) { mutableStateOf(uiState.countdownTimer.canBeStarted) }
        val pauseTimerButtonVisibility by remember(uiState.countdownTimer.canBePaused) { mutableStateOf(uiState.countdownTimer.canBePaused) }
        val replayTimerButtonVisibility by remember(uiState.countdownTimer.canBeReplay) { mutableStateOf(uiState.countdownTimer.canBeReplay) }
        Scaffold(modifier = modifier) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = uiState.countdownTimer.remainingSeconds.let { convertToCountdownTime(it) },
                    color = MaterialTheme.colors.primary,
                    fontSize = 64.sp,
                )
                Spacer(modifier = Modifier.height(64.dp))
                Row(
                    modifier = Modifier.animateContentSize(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AnimatedVisibility(
                        visible = startTimerButtonVisibility,
                        enter = scaleIn(),
                        exit = ExitTransition.None,
                    ) {
                        TimerButton(
                            onClick = onStartTimerClick,
                            imageVector = Icons.Default.PlayArrow,
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )
                    }
                    AnimatedVisibility(
                        visible = replayTimerButtonVisibility,
                        enter = scaleIn(),
                        exit = ExitTransition.None,
                    ) {
                        TimerButton(
                            onClick = onReplayTimerClick,
                            imageVector = Icons.Default.Replay,
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )
                    }
                    AnimatedVisibility(
                        visible = pauseTimerButtonVisibility,
                        enter = scaleIn(),
                        exit = ExitTransition.None,
                    ) {
                        TimerButton(
                            onClick = onPauseTimerClick,
                            imageVector = Icons.Default.Pause,
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TimerButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
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

private fun convertToCountdownTime(seconds: Int) = "%02d:%02d".format(seconds / 60, seconds % 60)