
package com.soumyadeep.pingme.presentation.feature_onboard.onboarding

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.R
import com.soumyadeep.pingme.common.button
import com.soumyadeep.pingme.presentation.viewmodel.RootViewModel
import androidx.compose.material3.Button
@Composable
fun onboard(
    navController: NavHostController,
) {
    val activity = LocalContext.current as? Activity

    // Prevents exiting app accidentally by back press
    BackHandler {
        activity?.moveTaskToBack(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        // Logo container (takes all vertical space above button area)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "PingMe Logo",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .aspectRatio(1f)
                    .sizeIn(maxWidth = 240.dp, maxHeight = 240.dp)
            )
        }

        // Bottom column with terms text + continue button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val light = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            val normal = MaterialTheme.colorScheme.onBackground

            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    appendStyled("By clicking continue, you agree to our ", light)
                    appendStyled("Terms of Service", normal)
                    appendStyled(" and ", light)
                    appendStyled("Privacy Policy", normal)
                },
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            // Reuse your custom button
            button(
                text = "Continue",
                onClick = {
                    navController.navigate("signin") {
                        popUpTo("splash") { inclusive = false } // keep splash for flow reset
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

// helper for annotated string
fun androidx.compose.ui.text.AnnotatedString.Builder.appendStyled(
    text: String,
    color: Color,
    fontSize: androidx.compose.ui.unit.TextUnit = 12.sp
) {
    pushStyle(SpanStyle(color = color, fontSize = fontSize))
    append(text)
    pop()
}
//@Composable
//fun onboard(navController: NavHostController) {
//    Column {
//        Text("Onboarding Screen")
//        Button(onClick = { navController.navigate("signin") }) { Text("Go to Sign In") }
//    }
//}