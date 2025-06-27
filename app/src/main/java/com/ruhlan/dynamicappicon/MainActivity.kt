package com.ruhlan.dynamicappicon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.google.gson.Gson
import com.ruhlan.dynamicappicon.extensions.changeEnabledComponent
import com.ruhlan.dynamicappicon.model.Icon
import com.ruhlan.dynamicappicon.ui.theme.DynamicAppIconTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 1
            }
        )

        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val stringJson = Firebase.remoteConfig.getString("launcher_icon")
                if (stringJson.isNotEmpty()) {
                    val gson = Gson()
                    val jsonModel= gson.fromJson(stringJson, Icon::class.java)
                    val data = Icon(
                        enable = jsonModel.enable,
                        disable = jsonModel.disable
                    )
                    changeEnabledComponent(
                        enabled = data.enable,
                        disabled = data.disable
                    )
                }
            } else {
                // handle the else case
            }
        }

        setContent {
            DynamicAppIconTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        textIsClicked = {

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    textIsClicked: () -> Unit = {}
) {
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable(onClick = textIsClicked)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DynamicAppIconTheme {
        Greeting("Android")
    }
}
