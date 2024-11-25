package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.myapplication.ui.theme.MyApplicationTheme
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize a mutable state variable to hold the joke


        // Launch a coroutine to fetch the joke

        setContent {
            MyApplicationTheme {


                   MyApp()

            }
        }
    }
}

// Fetch joke function as a suspending function
suspend fun sendChatRequest(apiKey: String, message: String): String {
    val chatRequest = ChatGPTRequest(
        model = "gpt-4-turbo",
        messages = listOf(Message(role = "user", content = message))
    )

    return try {
        val response: HttpResponse = client.post("https://api.openai.com/v1/chat/completions") {
            header("Authorization", "Bearer $apiKey")
            header("Content-Type", "application/json") // Ensure content type is set
            setBody(chatRequest)
        }

        if (response.status.isSuccess()) {
            val chatResponse: ChatGPTResponse = response.body()
            chatResponse.choices.firstOrNull()?.message?.content ?: "No response received"
        } else {
            Log.e("ChatGPTRequest", "API Error: ${response.status.value} - ${response.body<String>()}")
            "Error: Received ${response.status.value} from the API"
        }
    } catch (e: Exception) {
        Log.e("ChatGPTRequest", "Error sending chat request: ${e.message}")
        "Error: ${e.message ?: "Unknown error occurred."}"
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,navController:NavHostController) {

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(onClick = {
            navController.navigate("second_screen")
        }) { }
    }

}





