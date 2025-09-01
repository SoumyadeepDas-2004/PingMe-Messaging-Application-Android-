package com.soumyadeep.pingme.sandbox

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.window.Dialog

// ...keep all previous imports

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutComponentsComparison() {
    var showDialog by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Select Option") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Spacer
        Text("Above Spacer")
        Spacer(modifier = Modifier.height(32.dp))
        Text("Below Spacer")

        // Canvas
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray)
        ) {
            drawCircle(
                color = Color.Red,
                radius = size.minDimension / 4,
                center = Offset(size.width / 2, size.height / 2)
            )
        }

        // Dialog
        Button(onClick = { showDialog = true }) { Text("Show Dialog") }
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    Modifier
                        .size(200.dp, 150.dp)
                        .background(Color.White, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("This is a custom Dialog")
                }
            }
        }

        // AlertDialog
        Button(onClick = { showAlertDialog = true }) { Text("Show AlertDialog") }
        if (showAlertDialog) {
            AlertDialog(
                onDismissRequest = { showAlertDialog = false },
                title = { Text("Alert") },
                text = { Text("This is an AlertDialog") },
                confirmButton = {
                    TextButton(onClick = { showAlertDialog = false }) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { showAlertDialog = false }) { Text("Cancel") }
                }
            )
        }

        // DropdownMenu
        Box {
            Button(onClick = { expanded = true }) { Text(selectedOption) }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LayoutComponentsComparisonPreview() {
    LayoutComponentsComparison()
}
