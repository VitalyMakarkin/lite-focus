package org.example.litefocus.feature.countdowntimer.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.litefocus.feature.countdowntimer.presentation.CountdownTimerViewModel

@Composable
fun CountdownTimerScreen(
    modifier: Modifier = Modifier,
    viewModel: CountdownTimerViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(modifier = modifier) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = uiState.countdownTimer?.remainingTimeInMillis?.let { convertToCountdownTime(it) } ?: "",
                color = MaterialTheme.colors.primary,
                fontSize = 64.sp,
            )
            Spacer(modifier = Modifier.height(64.dp))
            Row {
                if (uiState.countdownTimer?.canBeStarted == true) {
                    TimerButton(
                        onClick = viewModel::startTimer,
                        imageVector = Icons.Default.PlayArrow ,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                if (uiState.countdownTimer?.canBePaused == true) {
                    TimerButton(
                        onClick = viewModel::pauseTimer,
                        imageVector = Icons.Default.Pause,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                if (uiState.countdownTimer?.canBeReplay == true) {
                    TimerButton(
                        onClick = viewModel::replayTimer,
                        imageVector = Icons.Default.Replay,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
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

private fun convertToCountdownTime(millis: Long): String {
    val seconds = millis / 1000L
    return "%02d:%02d".format(seconds / 60, seconds % 60)
}