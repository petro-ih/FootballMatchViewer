package com.footballmatchviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.footballmatchviewer.ui.screen.matches.MatchesScreen
import com.footballmatchviewer.ui.theme.FootballMatchViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                FootballMatchViewerTheme {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "match_screen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("match_screen") {
                            MatchesScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
