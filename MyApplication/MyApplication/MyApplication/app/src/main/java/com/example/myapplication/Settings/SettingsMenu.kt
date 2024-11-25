package com.example.myapplication.Settings

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.settingsViewModel



@Composable
fun SettingsMenu(navController: NavHostController, settingsViewModel: SettingsViewModel) {
    val selectedLanguage = settingsViewModel.selectedLanguage
    val showGrammaticalExamples = settingsViewModel.showGrammaticalExamples
    val showAdvancedOptions = settingsViewModel.showAdvancedOptions


    println(showAdvancedOptions)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            // Header Row with Back Arrow and Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { navController.navigate("home") } // Navigate to second_screen
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp)) // Space between the icon and text

                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 28.sp),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Language Selection Dropdown
            Text(text = "Select Target Language", style = MaterialTheme.typography.labelLarge)
            LanguageDropdown(selectedLanguage) { newLanguage ->
                settingsViewModel.updateLanguage(newLanguage)
                Log.d("SettingsMenu", "Selected Language changed to: $newLanguage")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Toggle for Grammatical Usage Examples
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Show Grammatical Usage Examples", style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = showGrammaticalExamples,
                    onCheckedChange = {
                        settingsViewModel.toggleGrammaticalExamples(it)
                        Log.d("SettingsMenu", "Show Grammatical Usage Examples changed to: $it")
                    }
                )
            }

            // Advanced Options Toggle
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Show Advanced Options", style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = showAdvancedOptions,
                    onCheckedChange = {
                        settingsViewModel.toggleAdvancedOptions(it)
                        Log.d("SettingsMenu", "Show Advanced Options changed to: $it")
                    }
                )
            }

            if (showAdvancedOptions) {
                AdvancedSettings(settingsViewModel)
            }
        }
    }
}



@Composable
fun LanguageDropdown(selectedLanguage: String, onLanguageSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("English", "Spanish", "French", "German", "Chinese", "Japanese","Arabic")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Dropdown button
        OutlinedButton(
            onClick = {
                expanded = !expanded
                println("Dropdown expanded state changed to: $expanded") // Print state change
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = selectedLanguage)
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                println("Dropdown dismissed") // Print state change
            }
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    text = { Text(language) },
                    onClick = {
                        onLanguageSelected(language)
                        expanded = false
                        println("Language selected: $language") // Print selected language
                    }
                )
            }
        }
    }
}

@Composable
fun AdvancedSettings(Settingsview:SettingsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Advanced Settings", style = MaterialTheme.typography.bodyLarge)

        // Additional settings (e.g., input validation, response format) could go here
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enable Detailed Parsing", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.weight(1f))
            var detailedParsing by remember { mutableStateOf(true) }
            Switch(
                checked = Settingsview.detailedParsing,
                onCheckedChange = {
                    Settingsview.toggleDetailedParsing(it)
                    println("Detailed Parsing changed to: $it") // Print state change
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Confidence Level", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.weight(1f))
            var showConfidence = Settingsview.showConfidence
            Switch(
                checked = showConfidence,
                onCheckedChange = {
                    Settingsview.toggleShowConfidence(it)
                    println("Confidence Level changed to: $it") // Print state change
                }
            )
        }
    }
}
