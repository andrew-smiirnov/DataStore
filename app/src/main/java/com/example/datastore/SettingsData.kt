package com.example.datastore

import com.example.datastore.ui.theme.Blue
import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    val textSize: Int = 40,
    val bgColor: ULong = Blue.value
)