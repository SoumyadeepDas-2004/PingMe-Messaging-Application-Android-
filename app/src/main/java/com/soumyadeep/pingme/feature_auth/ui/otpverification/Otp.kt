package com.soumyadeep.pingme.feature_auth.ui.otpverification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpInput(
    otpLength: Int = 4,
    onOtpEntered: (String) -> Unit
) {
    var otpValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        repeat(otpLength) { index ->
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(56.dp)
                    .background(
                        color = Color.LightGray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        focusManager.clearFocus(force = true)
                        focusRequester.requestFocus()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = otpValue.getOrNull(index)?.toString() ?: "_",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
        }
    }

    BasicTextField(
        value = otpValue,
        onValueChange = {
            if (it.length <= otpLength && it.all { char -> char.isDigit() }) {
                otpValue = it
                if (it.length == otpLength) {
                    onOtpEntered(it)
                    focusManager.clearFocus(force = true)
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        cursorBrush = SolidColor(Color.Transparent), // ✅ Hides the real cursor
        modifier = Modifier
            .focusRequester(focusRequester)
            .width(1.dp)
            .height(1.dp),
        singleLine = true
    )
}

@Composable
fun otp(navController: NavHostController, modifier: Modifier = Modifier) {
    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ✅ Top logo
            Text(
                text = "PingMe",
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(48.dp))

            // ✅ Form section
            Text(
                text = "Create an account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your phone no to sign up for this app",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
            OtpInput(
                otpLength = 4,
                onOtpEntered = { code ->
                    // Handle the OTP code here
                    println("Entered OTP: $code")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    // Handle click
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Continue",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xff828282), fontSize = 12.sp)) {
                        append("By clicking continue, you agree to our ")
                    }
                    withStyle(SpanStyle(color = Color.Black, fontSize = 12.sp)) {
                        append("Terms of Service")
                    }
                    withStyle(SpanStyle(color = Color(0xff828282), fontSize = 12.sp)) {
                        append(" and ")
                    }
                    withStyle(SpanStyle(color = Color.Black, fontSize = 12.sp)) {
                        append("Privacy Policy")
                    }
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    val navController = rememberNavController() // fake NavController for preview
    otp(navController)
}