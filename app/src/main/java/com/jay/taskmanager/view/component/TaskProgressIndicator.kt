package com.jay.taskmanager.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskProgressIndicator(completedTasks: Int) {
    val progress by remember { derivedStateOf { completedTasks.toFloat() / 100 } }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(20.dp),
            strokeWidth = 4.dp,
            trackColor = ProgressIndicatorDefaults.circularDeterminateTrackColor,
        )
        Text(text = "${(progress * 100).toInt()}%")
    }
}