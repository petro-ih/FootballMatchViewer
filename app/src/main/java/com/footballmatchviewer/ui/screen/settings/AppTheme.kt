package com.footballmatchviewer.ui.screen.settings

import androidx.annotation.StringRes
import com.footballmatchviewer.R

enum class AppTheme(
    @StringRes
    val nameRes: Int
) {
    SYSTEM(R.string.screen_settings_theme_system),
    DARK(R.string.screen_settings_theme_dark),
    LIGHT(R.string.screen_settings_theme_light),
}