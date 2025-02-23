package org.example.litefocus.feature.countdowntimer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Button(
                onClick = if (uiState.countdownTimer?.canBeStarted == true) viewModel::startTimer else viewModel::stopTimer,
                contentPadding = PaddingValues(all = 32.dp),
                shape = RoundedCornerShape(percent = 100),
            ) {
                Icon(
                    imageVector = if (uiState.countdownTimer?.canBeStarted == true) Icons.Default.PlayArrow else Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    }
}

private fun convertToCountdownTime(millis: Long): String {
    val seconds = millis / 1000L
    return "%02d:%02d".format(seconds / 60, seconds % 60)
}