package com.footballmatchviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.footballmatchviewer.ui.screen.Screen
import com.footballmatchviewer.ui.screen.settings.SettingsViewModel
import com.footballmatchviewer.ui.theme.FootballMatchViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val theme = settingsViewModel.appTheme.collectAsState()

            FootballMatchViewerTheme(theme.value) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MatchesScreen.route
                ) {
                    composable(Screen.MatchesScreen.route) { backStackEntry ->
                        Screen.MatchesScreen.create(backStackEntry, navController)
                    }
                    composable(Screen.SettingsScreen.route) { backStackEntry ->
                        Screen.SettingsScreen.create(backStackEntry, navController)
                    }
                    composable(
                        route = Screen.WebViewScreen.route,
                        arguments = Screen.WebViewScreen.arguments
                    ) { backStackEntry ->
                        Screen.WebViewScreen.create(backStackEntry, navController)
                    }
                }
            }
        }
    }
}
