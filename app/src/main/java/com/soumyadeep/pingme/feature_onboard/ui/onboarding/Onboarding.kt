package com.soumyadeep.pingme.feature_onboard.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.R
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface


@Composable
fun onboard() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background // or custom color
    ) {
//        var buttonVisible by remember { mutableStateOf(false) }

        // Trigger animation after composition
//        LaunchedEffect(true) {
////            delay(500) // optional: wait for logo/slogan
//            buttonVisible = true
//
//        }

        // Animate alpha and vertical offset
//        val buttonAlpha by animateFloatAsState(targetValue = if (buttonVisible) 1f else 0f)
//        val buttonTranslationY by animateFloatAsState(targetValue = if (buttonVisible) 0f else 50f)

        Box(
            modifier = Modifier
                .fillMaxSize()
//            .background(Color.White)
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "PingMe Logo",
//                modifier = Modifier
//                    .fillMaxWidth(0.75f)
////                    .aspectRatio(1f)
//                    .sizeIn(maxWidth = 240.dp, maxHeight = 240.dp)
//            )
                Icon(
                    painter = painterResource(id = R.drawable.logo), // your vector drawable XML
                    contentDescription = "PingMe Logo",
                    tint = MaterialTheme.colorScheme.primary,
//                tint = Color(0xFF0A84FF), //0xFF07689F change the tint color as needed
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .sizeIn(maxWidth = 240.dp, maxHeight = 240.dp)
                )

                Spacer(modifier = Modifier.weight(2f))
            }



            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.6f
                                ), fontSize = 12.sp
                            )
                        ) {
                            append("By clicking continue, you agree to our ")
                        }
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 12.sp
                            )
                        ) {
                            append("Terms of Service")
                        }
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.6f
                                ), fontSize = 12.sp
                            )
                        ) {
                            append(" and ")
                        }
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 12.sp
                            )
                        ) {
                            append("Privacy Policy")
                        }

                    },
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .graphicsLayer {
//                            alpha = buttonAlpha
//                            translationY = buttonTranslationY
//                        },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {

                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

