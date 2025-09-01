package com.soumyadeep.pingme.feature_call.ui.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallReceived
import androidx.compose.material.icons.filled.CallMade
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class CallLog(
    val name: String,
    val time: String,
    val type: String // "Incoming", "Outgoing", "Missed"
)

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calls() {
    Scaffold(
        topBar = {
//            TopAppBar(
//                title = { Text("Calls") },
//                actions = {
//                    IconButton(onClick = { /* start new call */ }) {
//                        Icon(Icons.Default.Call, contentDescription = "New Call")
//                    }
//                }
//            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(sampleCallLogs) { call ->
                CallItem(call)
            }
        }
    }
}
@Composable
fun CallItem(call: CallLog) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.2f) // or any color you want
        ),
//        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
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
