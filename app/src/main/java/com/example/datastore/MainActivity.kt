package com.example.datastore

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastore.ui.theme.Blue
import com.example.datastore.ui.theme.DataStoreTheme
import com.example.datastore.ui.theme.Green
import com.example.datastore.ui.theme.Red
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = ProtoDataStoreManager(this)

        setContent {
            DataStoreTheme {

                val settingsState = dataStoreManager
                    .getSettings()
                    .collectAsState(initial = SettingsData())


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(settingsState.value.bgColor)
                ) {
                    MainScreen(dataStoreManager, settingsState.value.textSize)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    dataStoreManager: ProtoDataStoreManager,
    textSize: Int
) {
    val coroutine = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.6f)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)
        ) {
            Text(
                text = "Some text",
                color = Color.White,
                fontSize = textSize.sp
            )
        }
        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsData(
                        20,
                        Blue.value
                    )
                )
            }
        }) {
            Text(text = "Blue")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsData(
                        30,
                        Red.value
                    )
                )
            }
        }) {
            Text(text = "Red")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsData(
                        40,
                        Green.value
                    )
                )
            }
        }) {
            Text(text = "Green")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveBgColor(Yellow.value)
            }
        }) {
            Text(text = "Yellow screen")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStoreManager.saveTextSize(50)
            }
        }) {
            Text(text = "Text max size")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
