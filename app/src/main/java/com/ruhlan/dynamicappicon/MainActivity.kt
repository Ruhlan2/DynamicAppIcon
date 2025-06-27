package com.ruhlan.dynamicappicon

import android.os.Bundle
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
import com.ruhlan.dynamicappicon.extensions.changeEnabledComponent
import com.ruhlan.dynamicappicon.ui.theme.DynamicAppIconTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DynamicAppIconTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        textIsClicked = {
                            changeEnabledComponent(
                                enabled = BuildConfig.main_activity_alias,
                                disabled = BuildConfig.main_activity,
                            )
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
