package com.soumyadeep.pingme.presentation.feature_chat.chatlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soumyadeep.pingme.R
import com.soumyadeep.pingme.model.ChatListModel
import java.text.SimpleDateFormat
import java.util.*

//data class ChatItemModel(
//    val name: String,
//    val lastMessage: String,
//    val time: String,
//    val unread: Boolean = false
//)
//
//// Sample chat list
//val chatList = listOf(
//    ChatItemModel("Alice Johnson", "Hey! Are you coming today?", "10:30 AM", true),
//    ChatItemModel("Bob Smith", "Sent the documents.", "9:45 AM"),
//    ChatItemModel("Charlie Brown", "Let's meet tomorrow.", "Yesterday", true),
//    ChatItemModel("David Lee", "Thanks!", "Yesterday"),
//    ChatItemModel("Eve Clark", "Happy Birthday!", "2 days ago"),
//    ChatItemModel("Alice Johnson", "Hey! Are you coming today?", "10:30 AM", true),
//    ChatItemModel("Bob Smith", "Sent the documents.", "9:45 AM"),
//    ChatItemModel("Charlie Brown", "Let's meet tomorrow.", "Yesterday", true),
//    ChatItemModel("David Lee", "Thanks!", "Yesterday"),
//    ChatItemModel("Eve Clark", "Happy Birthday!", "2 days ago"),
//    ChatItemModel("Alice Johnson", "Hey! Are you coming today?", "10:30 AM", true),
//    ChatItemModel("Bob Smith", "Sent the documents.", "9:45 AM"),
//    ChatItemModel("Charlie Brown", "Let's meet tomorrow.", "Yesterday", true),
//    ChatItemModel("David Lee", "Thanks!", "Yesterday"),
//    ChatItemModel("Eve Clark", "Happy Birthday!", "2 days ago")
//)

@Composable
fun ChatItem(
    chat: ChatListModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile image placeholder for now
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.name.ifBlank { chat.phoneNumber },
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = chat.lastMessage ?: "",
                fontSize = 14.sp,
                color = if (chat.unreadCount > 0) MaterialTheme.colorScheme.primary else Color.Gray,
                maxLines = 1
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = formatTimestamp(chat.lastMessageTime), // helper function
                fontSize = 12.sp,
                color = Color.Gray
            )
            if (chat.unreadCount > 0) {
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
fun formatTimestamp(timestamp: Long): String {
    if (timestamp == 0L) return ""
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(date)
}