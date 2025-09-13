package com.soumyadeep.pingme.presentation.feature_call.call

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallMade
import androidx.compose.material.icons.filled.CallReceived
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// data class
data class CallLog(
    val name: String,
    val time: String,
    val type: String // "Incoming", "Outgoing", "Missed"
)

// data class object list
val sampleCallLogs = listOf(
    CallLog("Alice Johnson", "10:30 AM", "Incoming"),
    CallLog("Bob Smith", "Yesterday", "Outgoing"),
    CallLog("Charlie Brown", "Mon", "Missed"),
    CallLog("David Lee", "Sun", "Incoming"),
    CallLog("Alice Johnson", "10:30 AM", "Incoming"),
    CallLog("Bob Smith", "Yesterday", "Outgoing"),
    CallLog("Charlie Brown", "Mon", "Missed"),
    CallLog("David Lee", "Sun", "Incoming"),
    CallLog("Alice Johnson", "10:30 AM", "Incoming"),
    CallLog("Bob Smith", "Yesterday", "Outgoing"),
    CallLog("Charlie Brown", "Mon", "Missed"),
    CallLog("David Lee", "Sun", "Incoming"),
)

// composable function to show call logs(uses data class object list)
@Composable
fun CallItem(call: CallLog) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.2f)// or any color you want
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = call.name, style = MaterialTheme.typography.titleMedium)
                Text(text = call.time, style = MaterialTheme.typography.bodyMedium)
            }
            when (call.type) {
                "Incoming" -> Icon(Icons.Default.CallReceived, contentDescription = "Incoming")
                "Outgoing" -> Icon(Icons.Default.CallMade, contentDescription = "Outgoing")
                "Missed" -> Icon(Icons.Default.Call, contentDescription = "Missed")
            }
        }
    }
}
