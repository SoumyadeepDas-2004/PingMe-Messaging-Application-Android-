package com.soumyadeep.pingme.feature_profile.ui.profileInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soumyadeep.pingme.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Button

@Composable
fun AboutSection(
    aboutText: String,
    onAboutTextChange: (String) -> Unit
) {
    BasicTextField(
        value = aboutText,
        onValueChange = onAboutTextChange,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 16.sp,
            color = Color.Black
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Column {
                Box {
                    if (aboutText.isEmpty()) {
                        Text(
                            text = "Write something about yourself...",
                            fontSize = 14.sp,
                            color = Color(0xFF636366)
                        )
                    }
                    innerTextField()
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun setprofileScreen() {
    var Username by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }

    AboutSection(
        aboutText = about,
        onAboutTextChange = { about = it }
    )
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {

            // Top bar
            Text(
                    text = "Set Profile",
                    color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Profile photo with camera icon
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF9E9E9E))
                    .align(Alignment.CenterHorizontally)
            ) {
                // Replace with your actual profile image
                // Image(painter = painterResource(R.drawable.profile), contentDescription = null)

                // Camera icon overlay (example system icon)
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = "Camera",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Name label
//            Text(
//                text = "Jacob W.",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )

//            Spacer(modifier = Modifier.height(16.dp))

            // Last Name input
            OutlinedTextField(
                value = Username,
                onValueChange = { Username = it },
                placeholder = {
                    Text(
                        text = "Username",
                        color = Color(0xFF636366)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue
                )



            )
            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = "Enter your name and add an optional profile photo.",
                fontSize = 14.sp,
                color = Color(0xFF636366),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Subtitle (occupation)
//            Text(
//                text = "Digital goodies designer - Pixsellz",
//                fontSize = 17.sp,
//                fontWeight = FontWeight.Medium,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Small details
//            Text(
//                text = "Any details such as age, occupation or city.\nExample: 23 y.o. designer from San Francisco.",
//                fontSize = 14.sp,
//                color = Color(0xFF636366),
//                modifier = Modifier.fillMaxWidth()
//            )
            AboutSection(
                aboutText = about,
                onAboutTextChange = { about = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Description
//            Text(
//                text = "Write something about yourself...",
//                fontSize = 14.sp,
//                color = Color(0xFF636366),
//                modifier = Modifier.fillMaxWidth()
//            )


            Spacer(modifier = Modifier.height(40.dp))

            // Add Account
//            Text(
//                text = "Continue",
//                color = Color(0xFF037EE5),
//                fontSize = 17.sp,
//                fontWeight = FontWeight.Medium,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        // Handle click
//                    }
//            )
            Button(
                onClick = { /* Handle click */ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)  // Good tap size
            ) {
                Text(
                    text = "Continue",
                    color = Color(0xFF037EE5),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            // Bottom handle
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
                    .width(134.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.Black)
            )
        }
    }
}

