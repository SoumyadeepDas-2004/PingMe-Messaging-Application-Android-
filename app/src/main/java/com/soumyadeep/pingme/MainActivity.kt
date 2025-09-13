package com.soumyadeep.pingme


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.soumyadeep.pingme.core.theme.PingMeTheme
import com.soumyadeep.pingme.presentation.feature_profile.setprofile.setprofile
import com.soumyadeep.pingme.presentation.feature_profile.editprofile.EditProfile
import com.soumyadeep.pingme.navigation.RootNavHost
import com.soumyadeep.pingme.presentation.feature_contact.contact.Contacts
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.background
import com.soumyadeep.pingme.presentation.feature_auth.signIn.signin
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import android.util.Log
import com.soumyadeep.pingme.navigation.AppRoot
import com.soumyadeep.pingme.presentation.feature_chat.chat.Chat
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_PingMe) // switch to main app theme
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            PingMeTheme {
                AppRoot()
//                testFirebase()
                val navController = rememberNavController()
//
////                 Directly show the Contacts screen
//                Chat(
//                    otherUserPhone = "+916291227288", // replace with a real/test phone number
//                    navController = navController
//                )
                }
            }
        }
    }
