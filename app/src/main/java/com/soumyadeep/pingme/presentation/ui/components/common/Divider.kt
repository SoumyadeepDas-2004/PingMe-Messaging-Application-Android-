package com.soumyadeep.pingme.presentation.ui.components.common

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun divider() {

    Divider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant // or .onSurface.copy(alpha = 0.12f)
    )
}