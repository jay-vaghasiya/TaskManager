package com.jay.taskmanager.view.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jay.taskmanager.model.datamodel.Priority
import com.jay.taskmanager.model.datamodel.Task
import com.jay.taskmanager.utils.StrawFordFont
import com.jay.taskmanager.view.component.ChipGroup
import com.jay.taskmanager.view.component.CustomTextField
import com.jay.taskmanager.view.component.PastOrPresentSelectableDates
import com.jay.taskmanager.viewModel.TaskStateViewModel
import com.jay.taskmanager.viewModel.TaskViewModel
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreationScreen(navController: NavHostController) {
    val viewModel: TaskViewModel = koinViewModel()
    val stateViewModel: TaskStateViewModel = koinViewModel()
    val cnxt = LocalContext.current
    val datePickerState = rememberDatePickerState(selectableDates = PastOrPresentSelectableDates)


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Create a Task", fontWeight = FontWeight.Medium)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CustomTextField(
                value = stateViewModel.title,
                onValueChange = { stateViewModel.onTitleChange(it) },
                label = "Title",
                isRequired = true,
                singleLine = true,
                isError = stateViewModel.titleError != null,

                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp),
                shape = RectangleShape,
                onClick = { stateViewModel.toggleDatePickerDialog() }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (stateViewModel.date == "") "Date" else stateViewModel.date,
                        fontFamily = StrawFordFont.FontFamily,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(Icons.Filled.CalendarMonth, contentDescription = null)
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )


            Log.d("datepickerDialog", stateViewModel.datePickerDialog.toString())
            if (stateViewModel.datePickerDialog) {
                DatePickerDialog(
                    onDismissRequest = { stateViewModel.toggleDatePickerDialog() },
                    confirmButton = {
                        TextButton(onClick = {
                            val selectedDate = datePickerState.selectedDateMillis?.let {
                                Instant.fromEpochMilliseconds(it)
                                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
                            }
                            val dateFormat = LocalDate.Format {
                                monthName(names = MonthNames.ENGLISH_ABBREVIATED)
                                char('/')
                                dayOfMonth()
                                char('/')
                                year()
                            }
                            selectedDate?.let { dateFormat.format(it) }
                                ?.let { stateViewModel.onDateChange(it) }
                            stateViewModel.toggleDatePickerDialog()
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { stateViewModel.toggleDatePickerDialog() }) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            ElevatedCard(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.elevatedCardElevation(16.dp),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Column(
                        Modifier
                            .matchParentSize()

                    ) {
                        CustomTextField(
                            value = stateViewModel.description.toString(),
                            onValueChange = { stateViewModel.onDescriptionChange(it) },
                            label = "Description",
                            isRequired = false,
                            maxLines = 4,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            "Priority",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                            fontFamily = StrawFordFont.FontFamily,
                            fontWeight = FontWeight.Medium
                        )


                        ChipGroup(
                            options = Priority.entries.toTypedArray(),
                            selectedOption = stateViewModel.selectedPriority,
                            onOptionSelected = { stateViewModel.onPriorityChange(it) }
                        )

                    }
                    Button(
                        onClick = {
                            if (stateViewModel.dateError?.isNotBlank() == true) {
                                Toast.makeText(cnxt, stateViewModel.dateError, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            val task = Task(
                                title = stateViewModel.title,
                                description = stateViewModel.description,
                                priority = stateViewModel.selectedPriority,
                                dueDate = stateViewModel.date
                            )
                            if (stateViewModel.validateAndSave()) {
                                viewModel.addTask(task)
                                navController.popBackStack()
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            "Save",
                            modifier = Modifier.padding(8.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }

                }


            }
        }
    }
}