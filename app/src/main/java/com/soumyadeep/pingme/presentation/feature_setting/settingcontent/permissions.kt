package com.soumyadeep.pingme.presentation.feature_setting.settingcontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soumyadeep.pingme.presentation.ui.components.common.SettingSwitch
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun permission() {
    var cameraPermission by remember { mutableStateOf(true) }
    var contactsPermission by remember { mutableStateOf(false) }
    var locationPermission by remember { mutableStateOf(true) }

    Column() {
        Text(
            text = "Permissions",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp,
            color=MaterialTheme.colorScheme.onBackground
        )

        SettingSwitch(
            title = "Camera",
            checked = cameraPermission,
            onCheckedChange = { cameraPermission = it },
            color=MaterialTheme.colorScheme.onBackground
        )

        SettingSwitch(
            title = "Contacts",
            checked = contactsPermission,
            onCheckedChange = { contactsPermission = it },
            color=MaterialTheme.colorScheme.onBackground
        )

        SettingSwitch(
            title = "Location",
            checked = locationPermission,
            onCheckedChange = { locationPermission = it },
            color=MaterialTheme.colorScheme.onBackground
        )
    }
}

