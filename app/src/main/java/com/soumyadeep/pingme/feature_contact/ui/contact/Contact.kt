package com.soumyadeep.pingme.feature_contact.ui.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Contacts() {
    val contacts = listOf(
        "Soumyadeep Das",
        "Ananya Roy",
        "Rahul Sen",
        "Priya Sharma",
        "Arjun Gupta",
        "Sneha Mukherjee",
        "Rohan Dey",
        "Mitali Ghosh",
        "Mitali Ghosh",
        "Mitali Ghosh",
        "Mitali Ghosh",
        "Mitali Ghosh",
        "Mitali Ghosh"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Option 1: Add People Nearby
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* TODO: handle click */ }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Place, contentDescription = null, tint = Color(0xFF00BFFF))
            Spacer(Modifier.width(8.dp))
            Text("Add People Nearby", fontSize = 16.sp, color = Color(0xFF00BFFF))
        }
//        HorizontalDivider(thickness = 1.dp, .Color.MaterialTheme.colorScheme.background))

        // Option 2: Invite Friends
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* TODO: handle click */ }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.PersonAdd, contentDescription = null, tint = Color(0xFF00BFFF))
            Spacer(Modifier.width(8.dp))
            Text("Invite Friends", fontSize = 16.sp, color = Color(0xFF00BFFF))
        }
//        HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDDDDD))

        // ✅ Contacts List
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(contacts) { contact ->
                ContactItem(name = contact)
//                HorizontalDivider(thickness = 1.dp, color = Color(0xFFEEEEEE))
            }
        }
    }
}

@Composable
fun ContactItem(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* handle click */ }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar circle with first letter
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF009688), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.first().toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
