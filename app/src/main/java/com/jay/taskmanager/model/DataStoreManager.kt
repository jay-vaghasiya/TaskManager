package com.jay.taskmanager.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val NAME_KEY = stringPreferencesKey("user_name")
    }

    // Save Name
    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
        }
    }

    // Read Name
    fun getName(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[NAME_KEY]
        }
    }
}