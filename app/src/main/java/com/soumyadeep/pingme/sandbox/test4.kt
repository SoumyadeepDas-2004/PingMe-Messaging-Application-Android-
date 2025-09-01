package com.soumyadeep.pingme.sandbox

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputControlsComparison() {
    // --- Checkbox ---
    var checked by remember { mutableStateOf(false) }

    // --- RadioButton ---
    val radioOptions = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }

    // --- Switch ---
    var switchOn by remember { mutableStateOf(false) }

    // --- Slider ---
    var sliderPosition by remember { mutableStateOf(0.5f) }

    // --- Stepper ---
    var stepperValue by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = { checked = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Checkbox: ${if (checked) "Checked" else "Unchecked"}")
        }

        // RadioButton
        Column {
            Text("RadioButton Selection: $selectedOption")
            radioOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(option)
                }
            }
        }

        // Switch
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = switchOn, onCheckedChange = { switchOn = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Switch is ${if (switchOn) "ON" else "OFF"}")
        }

        // Slider
        Column {
            Text("Slider value: ${sliderPosition.format(2)}")
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 0f..1f
            )
        }

        // Stepper (increment/decrement)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (stepperValue > 0) stepperValue-- }) { Text("-") }
            Spacer(modifier = Modifier.width(16.dp))
            Text("Value: $stepperValue")
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { stepperValue++ }) { Text("+") }
        }
    }
}

// Extension function to format float
fun Float.format(digits: Int) = "%.${digits}f".format(this)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InputControlsComparisonPreview() {
    InputControlsComparison()
}
