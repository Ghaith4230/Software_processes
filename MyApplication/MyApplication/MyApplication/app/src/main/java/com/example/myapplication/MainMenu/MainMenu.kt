package com.example.myapplication.MainMenu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.Settings.SettingsViewModel
import com.example.myapplication.mainViewModel
import com.example.myapplication.sendChatRequest
import com.example.myapplication.settingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainMenu(navHostController: NavHostController,MainMenuView: MainMenuViewModel,settingsViewModel: SettingsViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Sentence Analyzer",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            MyScreenContent(navController = navHostController,MainMenuView = MainMenuView, settingsViewModel = settingsViewModel)
        }
    }
}

@Composable
fun MyScreenContent(modifier: Modifier = Modifier, navController: NavHostController ,MainMenuView: MainMenuViewModel,settingsViewModel: SettingsViewModel) {
    // State variable to hold the text input
    var text = MainMenuView.text

    // Text field with label and placeholder
    TextField(
        value = text,
        onValueChange = { newText -> mainViewModel.updateText(newText) },
        label = { Text("Enter a sentence") },
        placeholder = { Text("Type here...") },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),

    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            onClick = {



                var response:String
                val msg = "Translate the following sentence: $text \n" +
                        "using the following settings:\n" +
                        "Language = ${settingsViewModel.selectedLanguage}\n" +
                        "Show grammatical Usage Examples = ${settingsViewModel.showGrammaticalExamples}\n" +
                        "Enable Detailed Parsing = ${settingsViewModel.detailedParsing}\n" +
                        "Show Confidence Level = ${settingsViewModel.showConfidence}\n" +
                        "\n" +
                        "The example below demonstrates the exact format and spacing for the response. Follow this template precisely. Ensure each section appears in the same order and amount of new lines. Do not include the section names in the response. Use three newlines between major sections and two newlines between elements within a section, the whole response must be in the specified in the language:\n" +
                        "\n" +
                        "translation(dont include in the response)\n" +
                        "I hate cucumbers.\n\n\n\n" +
                        "examples(dont include in the response)\n" +
                        "Example 1: \"jeg\" in \"Jeg hader agurker\" means \"I\" and can be used in \"Jeg elsker tomater\" meaning \"I love tomatoes.\"\n\n" +
                        "Example 2: \"hader\" in \"Jeg hader agurker\" means \"hate\" and can also indicate strong dislike, as in \"Jeg hader regnvejr\" meaning \"I hate rainy weather.\"\n" +
                        "\n" +
                        "\n" +
                        "\n" +

                        "detailed parsing(dont include in the response)\n" +
                        "Jeg hader agurker (\"I hate cucumbers\"):\n" +
                        "Structure: Subject-Verb-Object (S-V-O) pattern.\n" +
                        "Confidence Level: 95%.\n" +
                        "\n" +
                        "Hader regnvejr (\"hate rainy weather\"):\n" +
                        "Describes strong dislike for a condition or event.\n" +
                        "Confidence Level: 90%.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "confidence(dont include in the response)\n" +
                        "Overall Translation Confidence: High.\n" +
                        "Sentence Structure Confidence: 92%.\n" +
                        "Word Choice Confidence: 88%.\n"



                println(msg)
                CoroutineScope(Dispatchers.IO).launch {
                    response = sendChatRequest("apiKey", msg)
                    MainMenuView.updateResponse(response)

                    println(response)

                }

                navController.navigate("Reply_Screen")
            }
        ) {
            Text(
                "Analyze",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Button(
            onClick = {
                navController.navigate("settings_menu")
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Settings", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}
