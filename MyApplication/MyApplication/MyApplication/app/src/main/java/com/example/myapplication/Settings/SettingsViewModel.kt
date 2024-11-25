package com.example.myapplication.Settings

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SettingsViewModel : ViewModel() {
    var selectedLanguage by mutableStateOf("English")
    var showGrammaticalExamples by mutableStateOf(true)
    var showAdvancedOptions by mutableStateOf(false)
    var showConfidence by mutableStateOf(false)
    var detailedParsing by mutableStateOf(false)

    fun updateLanguage(newLanguage: String) {
        selectedLanguage = newLanguage
    }

    fun toggleGrammaticalExamples(show: Boolean) {
        showGrammaticalExamples = show
    }

    fun toggleAdvancedOptions(show: Boolean) {
        showAdvancedOptions = show
    }
    fun toggleShowConfidence(show: Boolean) {
        showConfidence= show
    }
    fun toggleDetailedParsing(show: Boolean) {
        detailedParsing = show
    }
}