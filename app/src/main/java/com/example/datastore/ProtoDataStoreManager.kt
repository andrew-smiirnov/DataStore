package com.example.datastore

import android.content.Context
import androidx.datastore.dataStore

private val Context.protoDataStore by dataStore("settings.json", SettingsSerializer)
class ProtoDataStoreManager(val context: Context){
    suspend fun saveBgColor(color: ULong){
        context.protoDataStore.updateData {data ->
            data.copy(bgColor = color)
        }
    }

    suspend fun saveTextSize(size: Int){
        context.protoDataStore.updateData {data ->
            data.copy(textSize = size)
        }
    }

    suspend fun saveSettings(settingsData: SettingsData){
        context.protoDataStore.updateData {
            settingsData
        }
    }

    fun getSettings() = context.protoDataStore.data
}
