package com.soumyadeep.pingme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.soumyadeep.pingme.core.theme.PingMeTheme
import com.soumyadeep.pingme.feature_onboard.ui.splash.splash


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_PingMe) // switch to main app theme
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PingMeTheme {
                splash()

            }
        }
    }
}