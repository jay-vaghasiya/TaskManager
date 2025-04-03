package com.jay.taskmanager.view.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomChip(
    selected: Boolean,
    onSelected: () -> Unit,
    label: String
) {
    FilterChip(
        onClick = onSelected,
        label = {
            Text(label)
        },

        modifier = Modifier.padding(horizontal = 8.dp),
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

@Composable
fun <T : Enum<T>> ChipGroup(options: Array<T>, selectedOption: T, onOptionSelected: (T) -> Unit) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        options.forEach { option ->
            CustomChip(
                selected = option == selectedOption,
                onSelected = { onOptionSelected(option) },
                label = option.name
            )
        }
    }
}
