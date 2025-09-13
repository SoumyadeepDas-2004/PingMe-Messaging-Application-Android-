//package com.soumyadeep.pingme.presentation.feature_onboard.splash
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.asPaddingValues
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.sizeIn
//import androidx.compose.foundation.layout.systemBars
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.soumyadeep.pingme.R
//import androidx.compose.material3.Icon
//import androidx.compose.ui.res.stringResource
//import androidx.compose.runtime.LaunchedEffect
//import kotlinx.coroutines.delay
//import com.soumyadeep.pingme.presentation.viewmodel.RootViewModel
//import androidx.compose.runtime.collectAsState
//
//@Composable
//fun splash(navController: NavHostController, viewModel: RootViewModel) {
//    val startDestination by viewModel.startDestination.collectAsState()
//    Column(
//        modifier = Modifier
//            .fillMaxSize() // takes the whole screen
//            .background(MaterialTheme.colorScheme.background) // dynamic day/night background
//            .padding(WindowInsets.systemBars.asPaddingValues()), // makes content adaptable to screen size
//        horizontalAlignment = Alignment.CenterHorizontally, // centers content horizontally
//        verticalArrangement = Arrangement.Center // centers content vertically
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.logo), // logo is vector image
//            contentDescription = "PingMe Logo",
//            tint = MaterialTheme.colorScheme.primary, // fixed brand color
//            modifier = Modifier
//                .sizeIn(maxWidth = 240.dp, maxHeight = 240.dp) // sets max size of logo
//                .aspectRatio(1f) // Keeps the aspect ratio square(square image)
//                .fillMaxWidth(0.5f) // take up 50% of the available width(weight increase results height increase upto max bounds, maintaining aspect ratio)
//        )
//        Text(
//            text = stringResource(R.string.splash_slogan),
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Medium,
//            color = Color(0xFF1A8CFF)
//        )
//
//    }
//    LaunchedEffect(Unit) {
//        delay(2000)
//        viewModel.decideStartDestination()
//    }
//
//    // Navigate once ViewModel provides a destination
//    LaunchedEffect(startDestination) {
//        startDestination?.let { destination ->
//            navController.navigate(destination) {
//                popUpTo("splash") { inclusive = true }
//            }
//        }
//    }
//}
//
//
//
//
//
//
package com.soumyadeep.pingme.presentation.feature_onboard.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.R
import com.soumyadeep.pingme.presentation.viewmodel.RootViewModel
import kotlinx.coroutines.delay
import androidx.hilt.navigation.compose.hiltViewModel

//@Composable
//fun splash(
//    navController: NavHostController,
//    viewModel: RootViewModel = hiltViewModel()
//) {
//    // collectAsState with explicit initial value (safe for Flow/StateFlow)
//    val startDestination by viewModel.startDestination.collectAsState(initial = null)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//            .padding(WindowInsets.systemBars.asPaddingValues()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "PingMe Logo",
//            tint = MaterialTheme.colorScheme.primary,
//            modifier = Modifier
//                .sizeIn(maxWidth = 240.dp, maxHeight = 240.dp)
//                .aspectRatio(1f)
//                .fillMaxWidth(0.5f)
//        )
//
//        Text(
//            text = stringResource(R.string.splash_slogan),
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Medium,
//            color = Color(0xFF1A8CFF)
//        )
//    }
//
//    // react to ViewModel update and navigate
//    LaunchedEffect(startDestination) {
//        startDestination?.let { destination ->
//            navController.navigate(destination) {
//                popUpTo("splash") { inclusive = true }
//            }
//        }
//    }
//}


@Composable
fun splash(
    navController: NavHostController,
    viewModel: RootViewModel = hiltViewModel()
) {
    val startDestination by viewModel.startDestination.collectAsState()

    // Start deciding destination
    LaunchedEffect(Unit) {
        viewModel.decideStartDestination()
    }

    // Navigate once destination is decided
    LaunchedEffect(startDestination) {
        if (startDestination != "splash") {
            navController.navigate(startDestination) {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "PingMe Logo",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .sizeIn(maxWidth = 240.dp, maxHeight = 240.dp)
                .aspectRatio(1f)
                .fillMaxWidth(0.5f)
        )

        Text(
            text = stringResource(R.string.splash_slogan),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A8CFF)
        )
    }
}
