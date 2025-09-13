package com.soumyadeep.pingme.presentation.feature_profile.editprofile

//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.soumyadeep.pingme.presentation.ui.components.editprofile.AppTopBar
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.soumyadeep.pingme.R
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.common.AppTopBar
//@Composable
//fun SettingRow(title: String, value: String, onClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick)
//            .padding(horizontal = 16.dp, vertical = 12.dp),
////            .background(MaterialTheme.colorScheme.surfaceVariant),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(title, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
//        Text(value, fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground)
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavHostController) {
    var aboutText by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            AppTopBar(
                title = "Edit Profile",
                showBack = true,
                showTextButton = true,
                textButtonText = "Done",
                onTextButtonClick = { navController.navigate("settings") },
                onBackClick = {
                    navController.navigate("settings") {
                        launchSingleTop = true
                    }
                }
            )
        },

        ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .clickable { /* open image picker */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = "Camera",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Soumyadeep Das",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )


            // Name Field
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Full Name") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Transparent),
                singleLine = true
            )

            // Bio / About
            OutlinedTextField(
                value = aboutText,
                onValueChange = { aboutText = it },
                placeholder = { Text("About yourself") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.Transparent),
                singleLine = false,
                maxLines = 2
            )

            Spacer(Modifier.height(16.dp))

            // Change Number
//            SettingRow(title = "Change Number", value = "+1 202 555 0147") {
//                /* TODO: Change number */
//            }
//
//            SettingRow(title = "Username", value = "Jacob Williams") {
//                /* TODO: Change username */
//            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDDDDD))
//            Spacer(Modifier.height(16.dp))
            // Add Account
            TextButton(
                onClick = { /* TODO: Add Account */ },
            ) {
                Text("Add Account", color = Color(0xFF1E88E5), fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Log Out
            TextButton(
                onClick = { /* TODO: Log Out */ },
            ) {
                Text("Log Out", color = Color.Red, fontSize = 16.sp)
            }
        }
    }


}

