package com.footballmatchviewer.ui.screen.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appLocaleManager: AppLocaleManager,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    companion object {
        val THEME_KEY = stringPreferencesKey("app_theme")
    }

    val appTheme = MutableStateFlow<AppTheme>(AppTheme.SYSTEM)
    val appLanguage = MutableStateFlow<Locale>(Locale.getDefault())
    val allLanguages = MutableStateFlow<List<Locale>>(emptyList())

    init {
        viewModelScope.launch {
            allLanguages.value = appLocaleManager.getSupportedLanguages()
            appLanguage.value = appLocaleManager.getCurrentLanguage()

            dataStore.data.collect { prefs ->
                prefs[THEME_KEY]
                    ?.let { prefsValue -> AppTheme.entries.firstOrNull { it.name == prefsValue } }
                    ?.let { appTheme.value = it }
            }
        }
    }

    fun setTheme(newTheme: AppTheme) {
        appTheme.value = newTheme
        viewModelScope.launch {
            dataStore.edit { it[THEME_KEY] = newTheme.name }
        }
    }

    fun setLanguage(locale: Locale) {
        appLocaleManager.changeLanguage(locale)
    }
}