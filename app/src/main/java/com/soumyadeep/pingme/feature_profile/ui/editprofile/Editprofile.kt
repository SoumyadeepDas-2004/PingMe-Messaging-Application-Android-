package com.soumyadeep.pingme.feature_profile.ui.editprofile

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Edit Profile", fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEDF2F4)
                ),
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back navigation */ }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    TextButton(onClick = { /* TODO: Save Profile */ }) {
                        Text("Done", color = Color(0xFF1E88E5)) // professional blue
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.CameraAlt,
                            contentDescription = "Add photo",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Jacob W.", fontWeight = FontWeight.SemiBold, fontSize = 18.sp,color = Color.Black)
                }
            }

//            Divider()

            // Name Field
            OutlinedTextField(
                value = "",
                onValueChange = { /* TODO */ },
                placeholder = { Text("Last Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )

            // Bio / About
            OutlinedTextField(
                value = "",
                onValueChange = { /* TODO */ },
                placeholder = { Text("About yourself") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = false,
                maxLines = 2
            )

            Spacer(Modifier.height(16.dp))

            // Change Number
            SettingRow(title = "Change Number", value = "+1 202 555 0147") {
                /* TODO: Change number */
            }

            SettingRow(title = "Username", value = "Jacob Williams") {
                /* TODO: Change username */
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDDDDD))
//            Spacer(Modifier.height(16.dp))
            // Add Account
            TextButton(
                onClick = { /* TODO: Add Account */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Account", color = Color(0xFF1E88E5), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Log Out
            TextButton(
                onClick = { /* TODO: Log Out */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log Out", color = Color.Red, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SettingRow(title: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 16.sp, color = Color.Black)
        Text(value, fontSize = 14.sp, color = Color.Gray)
    }
}

