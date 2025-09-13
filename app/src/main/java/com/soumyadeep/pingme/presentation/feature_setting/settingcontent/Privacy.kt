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
import com.soumyadeep.pingme.presentation.ui.components.common.SettingSwitch
@Composable
fun privacy() {
    var lastSeenEnabled by remember { mutableStateOf(true) }
    var readReceiptsEnabled by remember { mutableStateOf(true) }
    Column {
        Text(
            "Privacy",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }

        SettingSwitch(
            title = "Last Seen & Online",
            checked = lastSeenEnabled,
            onCheckedChange = { lastSeenEnabled = it },
            color = MaterialTheme.colorScheme.onBackground,
        )


        SettingSwitch(
            title = "Read Receipts",
            checked = readReceiptsEnabled,
            onCheckedChange = { readReceiptsEnabled = it },
            color = MaterialTheme.colorScheme.onBackground,
        )

}