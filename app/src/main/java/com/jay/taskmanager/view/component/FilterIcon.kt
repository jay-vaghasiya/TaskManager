package com.jay.taskmanager.view.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FilterIcon(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    onClick: () -> Unit,
    imageVector: ImageVector
) {
    val rotation by animateFloatAsState(targetValue = if (isActive) 0f else 180f, label = "Arrow Rotation")

    Row(
        modifier = modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(imageVector, contentDescription = "Filter Icon")
        }
        if (isActive) {
            Icon(
                imageVector = Icons.Filled.ArrowDownward,
                contentDescription = "Sorting Arrow",
                modifier = Modifier.rotate(rotation)
            )
        }
    }
}

