package com.soumyadeep.pingme.feature_chat.ui.chatlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.navigation.NavHostController

data class ChatItemModel(
    val name: String,
    val lastMessage: String,
    val time: String,
    val unread: Boolean = false
)

// Sample chat list
val chatList = listOf(
    ChatItemModel("Alice Johnson", "Hey! Are you coming today?", "10:30 AM", true),
    ChatItemModel("Bob Smith", "Sent the documents.", "9:45 AM"),
    ChatItemModel("Charlie Brown", "Let's meet tomorrow.", "Yesterday", true),
    ChatItemModel("David Lee", "Thanks!", "Yesterday"),
    ChatItemModel("Eve Clark", "Happy Birthday!", "2 days ago"),
    ChatItemModel("Alice Johnson", "Hey! Are you coming today?", "10:30 AM", true),
    ChatItemModel("Bob Smith", "Sent the documents.", "9:45 AM"),
    ChatItemModel("Charlie Brown", "Let's meet tomorrow.", "Yesterday", true),
    ChatItemModel("David Lee", "Thanks!", "Yesterday"),
    ChatItemModel("Eve Clark", "Happy Birthday!", "2 days ago"),
    ChatItemModel("Alice Johnson", "Hey! Are you coming today?", "10:30 AM", true),
    ChatItemModel("Bob Smith", "Sent the documents.", "9:45 AM"),
    ChatItemModel("Charlie Brown", "Let's meet tomorrow.", "Yesterday", true),
    ChatItemModel("David Lee", "Thanks!", "Yesterday"),
    ChatItemModel("Eve Clark", "Happy Birthday!", "2 days ago")
)

@Composable
fun ChatListScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(chatList) { chat ->
            ChatItem(chat)
        }
    }
}

@Composable
fun ChatItem(chat: ChatItemModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to chat detail */ }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material3.Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            tint = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = chat.lastMessage,
                fontSize = 14.sp,
//                color = if (chat.unread) Color(0xFF006400) else Color.Gray,
                color = if (chat.unread) MaterialTheme.colorScheme.primary else Color.Gray,
                maxLines = 1
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = chat.time,
                fontSize = 12.sp,
                color = Color.Gray
            )
            if (chat.unread) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}
