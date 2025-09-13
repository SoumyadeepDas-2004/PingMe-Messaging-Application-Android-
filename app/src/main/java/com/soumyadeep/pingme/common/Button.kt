package com.soumyadeep.pingme.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.height


@Composable
fun button(
    text: String,
    onClick: (() -> Unit)? = null,
    navController: NavController? = null, // optional
    navigateTo: String? = null,           // optional
    popupTo: String? = null,              // optional
    include: Boolean = false
) {
    Button(
        onClick = {
            onClick?.invoke() // call custom logic

            // optional navigation
            if (navController != null && navigateTo != null && popupTo != null) {
                navController.navigate(navigateTo) {
                    popUpTo(popupTo) { inclusive = include }
                }
            }
        },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    }
}