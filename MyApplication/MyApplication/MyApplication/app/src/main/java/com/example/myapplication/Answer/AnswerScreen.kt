package com.example.myapplication.Answer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ReplyScreen(navHostController: NavHostController, text: String) {
    val parts = text.split("\n\n\n") // Split by double newlines for sections

    // Safely assign each section to a variable
    val responseText = parts.getOrNull(0) ?: ""
    val grammaticalExamples = parts.getOrNull(1) ?: ""
    val detailedParsing = parts.getOrNull(2) ?: ""
    val confidenceLevel = parts.getOrNull(3) ?: ""

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {



            Spacer(modifier = Modifier.height(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { navHostController.navigate("home") } // Navigate to second_screen
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp)) // Space between the icon and text

                Text(
                    text = "ChatGPT's Analysis Report",
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 26.sp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }



            Spacer(modifier = Modifier.height(20.dp))



            Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(20.dp))

            // Section for Translation

            if(responseText != "") {
                SectionHeader("Translation")
                Text(
                    text = responseText,
                    style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 22.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ) // Added padding here
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Section for Detailed Parsing

            if(detailedParsing != "") {
                SectionHeader("Detailed Parsing")
                Text(
                    text = detailedParsing,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ) // Added padding here
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            if(grammaticalExamples != "") {
                SectionHeader("Grammatical Usage Examples")
                Text(
                    text = grammaticalExamples,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ) // Added padding here
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
            // Section for Confidence Level

            if(confidenceLevel != "") {
                SectionHeader("Confidence Level Analysis")
                Text(
                    text = confidenceLevel,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ) // Added padding here
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// Custom composable for section headers
@Composable
fun SectionHeader(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
