//package com.soumyadeep.pingme.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.soumyadeep.pingme.presentation.feature_profile.setprofile.setprofile
//import com.soumyadeep.pingme.presentation.feature_profile.editprofile.EditProfile
//import com.soumyadeep.pingme.presentation.feature_onboard.splash.splash
//import com.soumyadeep.pingme.presentation.feature_onboard.onboarding.onboard
//import  com.soumyadeep.pingme.presentation.feature_auth.signIn.signin
//import com.soumyadeep.pingme.presentation.feature_auth.otpverification.otp
//import com.soumyadeep.pingme.presentation.feature_chat.chat.Chat
//import androidx.compose.animation.ExperimentalAnimationApi
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable
//import androidx.compose.animation.*
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.ui.Modifier
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.background
//import androidx.compose.animation.core.tween
//import com.google.accompanist.navigation.animation.rememberAnimatedNavController
//import com.soumyadeep.pingme.presentation.feature_auth.signIn.signin
//import com.soumyadeep.pingme.presentation.feature_chat.AddFriendScreen
//import com.google.firebase.auth.FirebaseAuth
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import kotlinx.coroutines.tasks.await
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
////  NavHostController : which screen to go
//fun RootNavHost() {
//    val navController = rememberAnimatedNavController()
//    var startDestination by remember { mutableStateOf<String?>(null) } // null until we decide
//
//    LaunchedEffect(Unit) {
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        startDestination = if (currentUser != null) {
//            // check if profile exists
//            try {
//                val snapshot = com.google.firebase.database.FirebaseDatabase.getInstance()
//                    .getReference("users/${currentUser.uid}")
//                    .get()
//                    .await() // wait for result
//                if (snapshot.exists()) "main" else "set_profile"
//            } catch (e: Exception) {
//                "set_profile" // fallback if DB fails
//            }
//        } else {
//            "splash"
//        }
//    }
//
//    if (startDestination != null) {
//        AnimatedNavHost(
//            navController = navController,
//            startDestination = startDestination!!
//        ) {
//
//
//            // map route strings to screen composables
//            // route string is simply an unique identifier for a screen
//
//            composable(
//                "splash", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { splash(navController) }
//            composable(
//                "onboard", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { onboard(navController) }
//            composable(
//                "signin", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { signin(navController) }
//            composable(
//
//                "otp/{verificationId}",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { backStackEntry ->
//                val verificationId = backStackEntry.arguments?.getString("verificationId")
//                otp(
//                    navController = navController,
//                    verificationId = verificationId // ✅ just pass the value, types are inferred
//                ) // ✅ pass it in
//            }
////        composable("main") { MainScreenNav(navController) }
//            composable(
//                "set_profile", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { setprofile(navController) }
//            composable(
//                "add_friend",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) {
//                AddFriendScreen(navController = navController)
//            }
//
//            composable(
//                "main", enterTransition = {
//                    slideInHorizontally(
//                        initialOffsetX = { it }, // slide from right
//                        animationSpec = tween(0)
//                    ) + fadeIn(animationSpec = tween(0))
//                },
//                // Forward navigation: screen exits to right
//                exitTransition = {
//                    slideOutHorizontally(
//                        targetOffsetX = { it }, // slide to right
//                        animationSpec = tween(0)
//                    ) + fadeOut(animationSpec = tween(0))
//                },
//                // Back navigation: screen enters from left
//                popEnterTransition = {
//                    slideInHorizontally(
//                        initialOffsetX = { -it }, // slide from left
//                        animationSpec = tween(0)
//                    ) + fadeIn(animationSpec = tween(0))
//                },
//                // Back navigation: screen exits to left
//                popExitTransition = {
//                    slideOutHorizontally(
//                        targetOffsetX = { -it }, // slide to left
//                        animationSpec = tween(0)
//                    ) + fadeOut(animationSpec = tween(0))
//                }
//            ) {
//                MainScreenNav(navController)
//            }
//
//        }
//    }
//}
//
package com.soumyadeep.pingme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.soumyadeep.pingme.presentation.feature_profile.setprofile.setprofile
import com.soumyadeep.pingme.presentation.feature_profile.editprofile.EditProfile
import com.soumyadeep.pingme.presentation.feature_onboard.splash.splash
import com.soumyadeep.pingme.presentation.feature_onboard.onboarding.onboard
import  com.soumyadeep.pingme.presentation.feature_auth.signIn.signin
import com.soumyadeep.pingme.presentation.feature_auth.otpverification.otp
import com.soumyadeep.pingme.presentation.feature_chat.chat.Chat
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.animation.core.tween
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.soumyadeep.pingme.presentation.feature_auth.signIn.signin
import com.soumyadeep.pingme.presentation.feature_chat.AddFriendScreen
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.tasks.await
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.soumyadeep.pingme.presentation.viewmodel.RootViewModel
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalAnimationApi::class)
@Composable
//  NavHostController : which screen to go
//fun RootNavHost() {
//    val navController = rememberAnimatedNavController()
//    var startDestination by remember { mutableStateOf<String?>(null) } // null until we decide
//
//    LaunchedEffect(Unit) {
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        startDestination = if (currentUser == null) {
//            "splash" // not signed in, go to splash → signin → otp
//        } else {
//            try {
//                val snapshot = FirebaseDatabase.getInstance()
//                    .getReference("users/${currentUser.uid}")
//                    .get()
//                    .await()
//                if (snapshot.exists()) "main" else "set_profile"
//            } catch (e: Exception) {
//                "set_profile" // fallback
//            }
//        }
//    }
//
//    if (startDestination == null) {
//        // Show loading while deciding
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator()
//        }
//    } else {
//        AnimatedNavHost(
//            navController = navController,
//            startDestination = startDestination!!
//        ) {
//
//
//            // map route strings to screen composables
//            // route string is simply an unique identifier for a screen
//
//            composable(
//                "splash", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { splash(navController) }
//            composable(
//                "onboard", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { onboard(navController) }
//            composable(
//                "signin", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { signin(navController) }
//            composable(
//
//                "otp/{verificationId}",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { backStackEntry ->
//                val verificationId = backStackEntry.arguments?.getString("verificationId")
//                otp(
//                    navController = navController,
//                    verificationId = verificationId // ✅ just pass the value, types are inferred
//                ) // ✅ pass it in
//            }
////        composable("main") { MainScreenNav(navController) }
//            composable(
//                "set_profile", enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }) { setprofile(navController) }
//            composable(
//                "add_friend",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) {
//                AddFriendScreen(navController = navController)
//            }
//
//            composable(
//                "main", enterTransition = {
//                    slideInHorizontally(
//                        initialOffsetX = { it }, // slide from right
//                        animationSpec = tween(0)
//                    ) + fadeIn(animationSpec = tween(0))
//                },
//                // Forward navigation: screen exits to right
//                exitTransition = {
//                    slideOutHorizontally(
//                        targetOffsetX = { it }, // slide to right
//                        animationSpec = tween(0)
//                    ) + fadeOut(animationSpec = tween(0))
//                },
//                // Back navigation: screen enters from left
//                popEnterTransition = {
//                    slideInHorizontally(
//                        initialOffsetX = { -it }, // slide from left
//                        animationSpec = tween(0)
//                    ) + fadeIn(animationSpec = tween(0))
//                },
//                // Back navigation: screen exits to left
//                popExitTransition = {
//                    slideOutHorizontally(
//                        targetOffsetX = { -it }, // slide to left
//                        animationSpec = tween(0)
//                    ) + fadeOut(animationSpec = tween(0))
//                }
//            ) {
//                MainScreenNav(navController)
//            }
//
//        }
//    }
//}

//fun RootNavHost(
//    rootViewModel: RootViewModel = hiltViewModel()
//) {
//    val navController = rememberAnimatedNavController()
//    val startDestination = rootViewModel.startDestination.collectAsState().value
//
//    if (startDestination == null) {
//        // Show loading until ViewModel decides
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator()
//        }
//    } else {
//        AnimatedNavHost(
//            navController = navController,
//            startDestination = startDestination
//        ) {
//            composable(
//                "splash",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { splash(navController) }
//
//            composable(
//                "onboard",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { onboard(navController) }
//
//            composable(
//                "signin",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { signin(navController) }
//
//            composable(
//                "otp/{verificationId}",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { backStackEntry ->
//                val verificationId = backStackEntry.arguments?.getString("verificationId")
//                otp(navController = navController, verificationId = verificationId)
//            }
//
//            composable(
//                "set_profile",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { setprofile(navController) }
//
//            composable(
//                "add_friend",
//                enterTransition = { fadeIn(animationSpec = tween(500)) },
//                exitTransition = { fadeOut(animationSpec = tween(500)) }
//            ) { AddFriendScreen(navController) }
//
//            composable(
//                "main",
//                enterTransition = {
//                    slideInHorizontally(
//                        initialOffsetX = { it },
//                        animationSpec = tween(0)
//                    ) + fadeIn(animationSpec = tween(0))
//                },
//                exitTransition = {
//                    slideOutHorizontally(
//                        targetOffsetX = { it },
//                        animationSpec = tween(0)
//                    ) + fadeOut(animationSpec = tween(0))
//                },
//                popEnterTransition = {
//                    slideInHorizontally(
//                        initialOffsetX = { -it },
//                        animationSpec = tween(0)
//                    ) + fadeIn(animationSpec = tween(0))
//                },
//                popExitTransition = {
//                    slideOutHorizontally(
//                        targetOffsetX = { -it },
//                        animationSpec = tween(0)
//                    ) + fadeOut(animationSpec = tween(0))
//                }
//            ) { MainScreenNav(navController) }
//        }
//    }

fun RootNavHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("splash") { splash(navController) }
        composable("onboard") { onboard(navController) }
        composable("signin") { signin(navController) }
        composable("otp/{verificationId}") { backStackEntry ->
            val verificationId = backStackEntry.arguments?.getString("verificationId") ?: ""
            otp(navController, verificationId)
        }
        composable("set_profile") { setprofile(navController) }
        composable("main") { MainScreenNav(navController) }
    }
}