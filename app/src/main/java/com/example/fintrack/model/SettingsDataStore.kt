package com.example.fintrack.model

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        val IS_ENGLISH_LANGUAGE_KEY = booleanPreferencesKey("is_english_language")
    }

    // Функція для збереження мови (true - англійська, false - інша)
    suspend fun saveLanguage(isEnglish: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_ENGLISH_LANGUAGE_KEY] = isEnglish
        }
    }

    // Функція для отримання поточної мови
    suspend fun getLanguage(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[IS_ENGLISH_LANGUAGE_KEY] ?: true
    }
}