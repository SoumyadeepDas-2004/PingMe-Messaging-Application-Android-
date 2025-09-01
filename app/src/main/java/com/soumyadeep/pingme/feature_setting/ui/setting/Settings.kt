package com.soumyadeep.pingme.feature_setting.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings() {
    var lastSeenEnabled by remember { mutableStateOf(true) }
    var readReceiptsEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var selectedTheme by remember { mutableStateOf("System Default") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(vertical = 8.dp) // optional padding
    ) {
        // --- Profile Section ---
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Edit Profile */ }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFF009688), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("S", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        "Soumyadeep Das",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        "Hey there! I’m using PingMe",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        item { Divider(thickness = 1.dp, color = Color(0xFFDDDDDD)) }

        // --- Privacy Section ---
        item {
            Text(
                "Privacy",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            SettingSwitch(
                title = "Last Seen & Online",
                checked = lastSeenEnabled,
                onCheckedChange = { lastSeenEnabled = it },
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        item {
            SettingSwitch(
                title = "Read Receipts",
                checked = readReceiptsEnabled,
                onCheckedChange = { readReceiptsEnabled = it },
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        item { Divider(thickness = 1.dp, color = Color(0xFFDDDDDD)) }

        // --- Personalization Section ---
        item {
            Text(
                "Personalization",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            SettingOption("Theme", selectedTheme, color = MaterialTheme.colorScheme.onBackground) {
                // TODO: Show theme selection dialog
            }
        }
        item {
            SettingOption("Accent Color", "Teal", color = MaterialTheme.colorScheme.onBackground) {
                // TODO: Show accent color picker
            }
        }
        item { Divider(thickness =1.dp, color = Color(0xFFDDDDDD)) }

        // --- Notifications Section ---
        item {
            Text(
                "Notifications",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            SettingSwitch(
                title = "Enable Notifications",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it },
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        item { Divider(thickness = 1.dp, color = Color(0xFFDDDDDD)) }

        // --- General Section ---
        item {
            Text(
                "General",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            SettingOption("Language", "English", color = MaterialTheme.colorScheme.onBackground) {
                /* open language selector */
            }
        }
        item {
            SettingOption("About", "Version 1.0.0", color = MaterialTheme.colorScheme.onBackground) {
                /* show about */
            }
        }
    }
}


@Composable
fun SettingSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    color: Color = MaterialTheme.colorScheme.onSurface // default text color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = color,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.scale(0.8f),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White, // clean thumb
                checkedTrackColor = MaterialTheme.colorScheme.primary, // Professional Blue track when ON
//                uncheckedThumbColor = Color(0xFFB0BEC5), // Cool Gray thumb when OFF
//                uncheckedTrackColor = Color(0xFFE0E0E0), // Soft Gray track when OFF
//                checkedBorderColor = Color(0xFF1565C0), // subtle border around ON thumb
//                uncheckedBorderColor = Color(0xFFB0BEC5) // subtle border around OFF thumb
            )
        )
    }
}

@Composable
fun SettingOption(
    title: String,
    value: String,
    color: Color = MaterialTheme.colorScheme.onSurface, // default
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = color,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = color.copy(alpha = 0.7f) // lighter shade for value
        )
    }
}

