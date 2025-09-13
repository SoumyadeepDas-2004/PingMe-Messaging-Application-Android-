package com.soumyadeep.pingme.presentation.ui.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
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
            .padding(horizontal = 16.dp)
        .clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = color,
            style = MaterialTheme.typography.bodyLarge.copy(color = color),
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