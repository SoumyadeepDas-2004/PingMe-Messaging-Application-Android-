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
//import com.soumyadeep.pingme.feature_setting.ui.setting.SettingSwitch
import com.soumyadeep.pingme.presentation.ui.components.common.SettingSwitch

@Composable
fun notification() {
    Column{
        var notificationsEnabled by remember { mutableStateOf(true) }
            Text(
                "Notifications",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )


            SettingSwitch(
                title = "Enable Notifications",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it },
                color = MaterialTheme.colorScheme.onBackground
            )

    }
}