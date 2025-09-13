package com.soumyadeep.pingme.presentation.feature_setting.settingcontent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.soumyadeep.pingme.feature_setting.ui.setting.SettingOption
import com.soumyadeep.pingme.presentation.ui.components.common.SettingOption
import com.soumyadeep.pingme.presentation.ui.components.common.AppDialog
@Composable
fun personalization() {

    var selectedTheme by remember { mutableStateOf("System Default") }
    var showDialog by remember { mutableStateOf(false) } // controls dialog visibility
    Column {

        Text(
            "Personalization",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )


        SettingOption("Theme", selectedTheme, color = MaterialTheme.colorScheme.onBackground) {
            showDialog = true
        }


        SettingOption("Accent Color", "Teal", color = MaterialTheme.colorScheme.onBackground) {
            // TODO: Show accent color picker
        }
        if (showDialog) {
            AppDialog(
                title = "Choose Theme",
                message = "Select your preferred theme mode.",
                confirmText = "Apply",
                dismissText = "Cancel",
                onConfirm = {
                    selectedTheme = "Dark" // Example update
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
    }
