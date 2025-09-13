package com.soumyadeep.pingme.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.rememberNavController
import com.soumyadeep.pingme.presentation.viewmodel.RootViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
@Composable
fun AppRoot(rootViewModel: RootViewModel = hiltViewModel()) {
    val focusManager = LocalFocusManager.current
    val navController = rememberNavController()
    val startDestination by rootViewModel.startDestination.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                indication = null, // removes ripple
                interactionSource = remember { MutableInteractionSource() }
            ) {
                // dismiss keyboard / clear text field focus on any background tap
                focusManager.clearFocus()
            }
    ) {
        RootNavHost(navController = navController, startDestination = startDestination) // your existing navigation
        // ToastHost() // global toast overlay (if you add later)
    }
}
