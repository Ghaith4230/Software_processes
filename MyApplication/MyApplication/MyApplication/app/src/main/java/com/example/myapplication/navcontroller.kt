package com.example.myapplication
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Answer.ReplyScreen
import com.example.myapplication.MainMenu.MainMenu
import com.example.myapplication.MainMenu.MainMenuViewModel
import com.example.myapplication.Settings.SettingsMenu
import com.example.myapplication.Settings.SettingsViewModel
import android.content.Context
import java.io.File


val settingsViewModel = SettingsViewModel()

val mainViewModel = MainMenuViewModel()

@Composable
fun MyApp() {
    println("the selected langauge is" + settingsViewModel.selectedLanguage)
    val navController = rememberNavController()
    NavHostSetup(navController = navController)
}

@Composable
fun NavHostSetup(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Define the home screen route
        composable("home") {
            MainMenu(navController, mainViewModel,settingsViewModel)
        }

        // Define the second screen route

        composable("settings_menu") {
            SettingsMenu(navController, settingsViewModel)
        }

        composable("Reply_Screen") {
            ReplyScreen(navController, mainViewModel.getAnswer())
        }
    }
}
