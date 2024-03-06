package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastore.ui.theme.Blue
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(val context: Context) {

    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { pref ->
            pref[intPreferencesKey("text_size")] = settingsData.textSize
            pref[longPreferencesKey("bg_color")] = settingsData.bgColor.toLong()
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsData(
            pref[intPreferencesKey("text_size")] ?: 40,
            pref[longPreferencesKey("bg_color")] ?.toULong() ?: Blue.value
        )
    }
}