package com.soumyadeep.pingme.presentation.feature_auth.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.hilt.navigation.compose.hiltViewModel
import com.soumyadeep.pingme.presentation.viewmodel.FeatureAuthViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import com.soumyadeep.pingme.presentation.viewmodel.AuthState
import androidx.compose.runtime.LaunchedEffect
import android.widget.Toast
import androidx.activity.compose.BackHandler
import com.soumyadeep.pingme.model.Country
import androidx.compose.material3.CircularProgressIndicator
import com.soumyadeep.pingme.common.button
import androidx.compose.material3.OutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signin(
    navController: NavHostController,
    phoneAuthViewModel: FeatureAuthViewModel = hiltViewModel()
) {
    // Reset auth state only once when screen loads
    LaunchedEffect(Unit) {
        phoneAuthViewModel.resetAuthState()
    }

    val authState by phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current
    val activity = context as? Activity ?: return

    var phoneNumber by remember { mutableStateOf("") }
    val countries = rememberCountries(context)
    var selectedCountry by remember { mutableStateOf(countries.firstOrNull() ?: Country("India", "+91", "🇮🇳")) }

    // Handle back press
    BackHandler {
        navController.navigate("onboard") {
            popUpTo("splash") { inclusive = false }
            launchSingleTop = true
        }
    }

    // UI
    signinui(
        navController = navController,
        phoneNumber = phoneNumber,
        onPhoneChange = { phoneNumber = it },
        selectedCountry = selectedCountry,
        onCountryChange = { selectedCountry = it },
        countries = countries,
        isLoading = authState is AuthState.Loading,
        onContinueClick = {
            val cleanNumber = phoneNumber.filter { it.isDigit() }
            val fullNumber = selectedCountry.code + cleanNumber

            if (cleanNumber.length !in 6..12) {
                Toast.makeText(context, "Enter valid phone number", Toast.LENGTH_SHORT).show()
                return@signinui
            }

            // ✅ Only send OTP when user taps Continue
            phoneAuthViewModel.sendVerificationCode(fullNumber, activity)
        }
    )

    // Handle auth state changes
    LaunchedEffect(authState) {
        when (val state = authState) {
            is AuthState.CodeSent -> {
                // Navigate to OTP screen when code is sent
                navController.navigate("otp/${state.verificationId}") {
                    popUpTo("signin") { inclusive = true }
                    launchSingleTop = true
                }
            }
            is AuthState.Success -> {
                val user = state.user

                if (user.name.isEmpty() || user.status.isEmpty()) {
                    // Profile incomplete → go to set_profile
                    navController.navigate("set_profile") {
                        popUpTo("splash") { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    // Profile complete → go to main
                    navController.navigate("main") {
                        popUpTo("splash") { inclusive = true }
                        launchSingleTop = true
                    }
                }

                // Reset auth state to avoid repeated navigation
                phoneAuthViewModel.resetAuthState()
            }
            is AuthState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

}

@Composable
fun signinui(
    navController: NavHostController,
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,
    selectedCountry: Country,
    onCountryChange: (Country) -> Unit,
    countries: List<Country>,
    onContinueClick: () -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        Box(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "PingMe",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = "Create an account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Enter your phone no to sign up for this app",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                PhoneNumberInput(
                    phoneNumber = phoneNumber,
                    onPhoneChange = onPhoneChange,
                    selectedCountry = selectedCountry,
                    onCountryChange = onCountryChange,
                    countries = countries
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                button(
                    text = "Continue",
                    onClick = onContinueClick // ✅ Only call OTP here
                )
            }
        }
    }
}
//@Composable
//fun signin(navController: NavHostController) {
//    var phone by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("SignIn Screen")
//
//        OutlinedTextField(
//            value = phone,
//            onValueChange = { phone = it },
//            label = { Text("Phone Number") },
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                if (phone.isNotBlank()) {
//                    // Pass phone to your ViewModel for OTP generation
//                    navController.navigate("otp/123456") // test OTP
//                }
//            }
//        ) {
//            Text("Send OTP")
//        }
//    }
//}
