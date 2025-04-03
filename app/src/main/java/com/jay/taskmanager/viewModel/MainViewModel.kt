package com.jay.taskmanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.taskmanager.model.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserNameViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _name = MutableStateFlow<String?>(null)
    val name: StateFlow<String?> = _name

    init {
        viewModelScope.launch {
            dataStoreManager.getName().collect { storedName ->
                _name.value = storedName
            }
        }
    }

    fun saveUserName(name: String) {
        viewModelScope.launch {
            dataStoreManager.saveName(name)
        }
    }
}