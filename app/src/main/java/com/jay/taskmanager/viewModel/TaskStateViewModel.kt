package com.jay.taskmanager.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jay.taskmanager.model.datamodel.Priority

class TaskStateViewModel : ViewModel() {
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf<String?>("")
        private set
    var date by mutableStateOf("")
        private set
    var selectedPriority by mutableStateOf(Priority.LOW)
        private set
    var datePickerDialog by mutableStateOf(false)
        private set

    var titleError by mutableStateOf<String?>(null)
        private set
    var dateError by mutableStateOf<String?>(null)
        private set


    fun onTitleChange(newTitle: String) {
        title = newTitle
        titleError = if (newTitle.isBlank()) "Title is required" else null
    }

    fun onDescriptionChange(newDescription: String?) {
        description = newDescription
    }

    fun onDateChange(newDate: String) {
        date = newDate
        dateError = if (newDate.isBlank()) "Date is required" else null
    }

    fun onPriorityChange(newPriority: Priority) {
        selectedPriority = newPriority

    }

    fun toggleDatePickerDialog() {
        datePickerDialog = !datePickerDialog
    }

    fun validateAndSave(): Boolean {
        titleError = if (title.isBlank()) "Title is required" else null
        dateError = if (date.isBlank()) "Date is required" else null

        return titleError == null && dateError == null
    }
}
