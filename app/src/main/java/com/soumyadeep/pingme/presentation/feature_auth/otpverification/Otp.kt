package com.soumyadeep.pingme.presentation.feature_auth.otpverification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
//import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import com.soumyadeep.pingme.presentation.ui.components.otpscreen.OtpInput
import androidx.compose.foundation.layout.Arrangement
import com.google.firebase.database.FirebaseDatabase
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.soumyadeep.pingme.model.feature_auth_user
import androidx.compose.runtime.LaunchedEffect
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.common.button
import com.soumyadeep.pingme.presentation.ui.components.otpscreen.OtpInput
import com.soumyadeep.pingme.presentation.viewmodel.AuthState
import com.soumyadeep.pingme.presentation.viewmodel.FeatureAuthViewModel
import kotlinx.coroutines.tasks.await
@Composable
fun OtpScreenContent(
    navController: NavHostController,
    otpCode: String,
    onOtpChange: (String) -> Unit,
    onSubmit: () -> Unit,
    errorMessage: String? = null
) {
    BackHandler {
        navController.navigate("signin") {
            popUpTo("splash") { inclusive = false }
            launchSingleTop = true
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header + OTP input
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "PingMe",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                Text(
                    text = "Enter the 6-digit OTP",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                OtpInput(
                    otpLength = 6,
                    onOtpEntered = { onOtpChange(it) }
                )

                // Show error below input if present
                if (!errorMessage.isNullOrEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Submit button
            button(
                text = "Continue",
                onClick = onSubmit,           // remove popupTo screen too
            )
        }
    }
}
@Composable
fun otp(
    navController: NavHostController,
    verificationId: String?,
    phoneAuthViewModel: FeatureAuthViewModel = hiltViewModel()
) {
    var otpCode by remember { mutableStateOf("") }
    val authState by phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current

    when (val state = authState) {
        is AuthState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is AuthState.Success -> {
            LaunchedEffect(Unit) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                if (currentUser != null) {
                    val userRef = FirebaseDatabase.getInstance()
                        .getReference("users/${currentUser.uid}")

                    // Only write phone if it doesn't exist yet
//                    val snapshot = userRef.child("phone").get().await()
//                    if (!snapshot.exists() || snapshot.value == null) {
//                        userRef.child("phone").setValue(currentUser.phoneNumber ?: "test-number")
//                    }
                    val snapshot = userRef.child("phoneNumber").get().await()
                    if (!snapshot.exists() || snapshot.value == null) {
                        userRef.child("phoneNumber").setValue(currentUser.phoneNumber ?: "test-number")
                    }
                    navController.navigate("set_profile") {
                        popUpTo("otp/$verificationId") { inclusive = true }
                        launchSingleTop = true
                    }
                }
//                phoneAuthViewModel.resetAuthState()
            }
        }

        is AuthState.Error -> {
            OtpScreenContent(
                navController = navController,
                otpCode = otpCode,
                onOtpChange = { otpCode = it },
                onSubmit = {
                    if (otpCode.length == 6) {
                        phoneAuthViewModel.verifyCode(otpCode, verificationId, context)
                    } else {
                        Toast.makeText(context, "Enter a valid 6-digit OTP", Toast.LENGTH_SHORT).show()
                    }
                },
                errorMessage = state.message
            )
        }

        else -> {
            OtpScreenContent(
                navController = navController,
                otpCode = otpCode,
                onOtpChange = { otpCode = it },
                onSubmit = {
                    if (otpCode.length == 6) {
                        phoneAuthViewModel.verifyCode(otpCode, verificationId, context)
                    } else {
                        Toast.makeText(context, "Enter a valid 6-digit OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}
//@Composable
//fun otp(navController: NavHostController, verificationId: String) {
//    Column {
//        Text("OTP Screen ID: $verificationId")
//        Button(onClick = { navController.navigate("set_profile") }) { Text("Verify OTP → Set Profile") }
//    }
//}