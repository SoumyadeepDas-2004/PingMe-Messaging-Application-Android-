package com.soumyadeep.pingme.presentation.feature_setting.settingcontent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.soumyadeep.pingme.feature_setting.ui.setting.SettingOption
import com.soumyadeep.pingme.presentation.ui.components.common.SettingOption
@Composable
fun general() {
    Column{
        Text(
            "General",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }

        SettingOption("Language", "English", color = MaterialTheme.colorScheme.onBackground) {
            /* open language selector */

    }

        SettingOption("About", "Version 1.0.0", color = MaterialTheme.colorScheme.onBackground) {
            /* show about */
        }


}
