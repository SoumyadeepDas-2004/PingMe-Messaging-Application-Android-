package com.soumyadeep.pingme.presentation.feature_contact.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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