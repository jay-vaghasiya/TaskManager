package com.jay.taskmanager.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRightAlt
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jay.taskmanager.model.datamodel.Task
import com.jay.taskmanager.utils.StrawFordFont

@Composable
fun TaskCard(modifier: Modifier = Modifier, task: Task, onClick: (Int) -> Unit) {
    ElevatedCard(
        onClick = { onClick(task.id) },
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.elevatedCardElevation(12.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = task.title,
                fontFamily = StrawFordFont.FontFamily,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.dueDate,
                    fontFamily = StrawFordFont.FontFamily,
                    modifier = Modifier

                        .padding(vertical = 4.dp),
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = task.priority.toString(),
                    fontFamily = StrawFordFont.FontFamily,
                    maxLines = 2,
                    modifier = Modifier

                        .padding(vertical = 4.dp),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            if(task.isCompleted){
                Text(
                    text = "Task Completed",
                    fontFamily = StrawFordFont.FontFamily,
                    modifier = Modifier

                        .padding(vertical = 4.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Learn more",
                    fontFamily = StrawFordFont.FontFamily,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall
                )
                Icon(Icons.AutoMirrored.Rounded.ArrowRightAlt, contentDescription = null)
            }

        }


    }

}