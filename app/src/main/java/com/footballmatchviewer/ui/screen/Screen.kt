package com.footballmatchviewer.ui.screen

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.footballmatchviewer.ui.screen.webview.WebViewScreen

sealed class Screen(val route: String) {
    object MatchesScreen : Screen("matches_screen") {
        @Composable
        fun create(navBackStackEntry: NavBackStackEntry, navController: NavController) =
            com.footballmatchviewer.ui.screen.matches.MatchesScreen(navController)
    }

    object SettingsScreen : Screen("settings_screen") {
        @Composable
        fun create(navBackStackEntry: NavBackStackEntry, navController: NavController) =
            com.footballmatchviewer.ui.screen.settings.SettingsScreen(navController)
    }

    data class WebViewScreen(
        val titleRes: Int,
        val url: String
    ) : Screen("webview_screen/$titleRes/${Uri.encode(url)}") {
        companion object {
            private const val QUERY_TITLE_RES = "titleRes"
            private const val QUERY_ENCODED_URL = "encodedUrl"
            const val route = "webview_screen/{$QUERY_TITLE_RES}/{$QUERY_ENCODED_URL}"
            val arguments = listOf(
                navArgument("titleRes") { type = NavType.IntType },
                navArgument("encodedUrl") { type = NavType.StringType }
            )

            @Composable
            fun create(
                navBackStackEntry: NavBackStackEntry,
                navController: NavController
            ) {
                val arguments = requireNotNull(navBackStackEntry.arguments)
                with(arguments) {
                    val titleRes = requireNotNull(getInt(QUERY_TITLE_RES))
                    val encodedUrl = requireNotNull(getString(QUERY_ENCODED_URL))
                    val url = Uri.decode(encodedUrl)

                    WebViewScreen(
                        titleRes = titleRes,
                        url = url,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }

}