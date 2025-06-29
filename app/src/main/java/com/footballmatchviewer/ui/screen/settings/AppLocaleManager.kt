package com.footballmatchviewer.ui.screen.settings

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

class AppLocaleManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun getSupportedLanguages(): List<Locale> = listOf(
        Locale.ENGLISH,
        Locale("UK"),
        Locale("PT")
    )

    fun changeLanguage(locale: Locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(locale.language)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale.language))
        }
    }

    fun getCurrentLanguage(): Locale {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                ?.applicationLocales
                ?.get(0)
        } else {
            AppCompatDelegate.getApplicationLocales().get(0)
        }
        return Locale(locale?.language ?: Locale.ENGLISH.language)
    }
}