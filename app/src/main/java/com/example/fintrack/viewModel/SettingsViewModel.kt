package com.example.fintrack.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.model.SettingsDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context) : ViewModel() {

    private val settingsDataStore = SettingsDataStore(context)
    private val _isEnglishLanguage = MutableStateFlow<Boolean>(false)
    val isEnglishLanguage: StateFlow<Boolean> = _isEnglishLanguage

    init {
        viewModelScope.launch {
            _isEnglishLanguage.value = settingsDataStore.getLanguage()
        }
    }

    fun saveLanguage(isEnglish: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveLanguage(isEnglish)
            _isEnglishLanguage.value = isEnglish
        }
    }
}