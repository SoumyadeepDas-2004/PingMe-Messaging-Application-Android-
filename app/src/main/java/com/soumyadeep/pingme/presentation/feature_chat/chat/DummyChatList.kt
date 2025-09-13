package com.soumyadeep.pingme.presentation.feature_chat.chat


//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.widthIn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.runtime.remember
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Text
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.soumyadeep.pingme.model.Message
//import com.google.firebase.auth.FirebaseAuth
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale

//data class ChatMessage(val text: String, val isMine: Boolean)
//
//val dummyMessages = listOf(
//    ChatMessage("This is the main chat template", true),
//    ChatMessage("Oh?", false),
//    ChatMessage("Cool", false),
//    ChatMessage("How does it work?", false),
//    ChatMessage(
//        "You just edit any text to type in the conversation you want to show, and delete any bubbles you don’t want to use",
//        true
//    ),
//    ChatMessage("Boom!", true),
//    ChatMessage("Hmmm", false),
//    ChatMessage("I think I get it", false),
//    ChatMessage("Will head to the Help Center if I have more questions tho", false),
//    ChatMessage("Boom!", true),
//    ChatMessage("Hmmm", false),
//    ChatMessage("I think I get it", false),
//    ChatMessage("Will head to the Help Center if I have more questions tho", false),
//    ChatMessage("Boom!", true),
//    ChatMessage("Hmmm", false),
//    ChatMessage("I think I get it", false),
//    ChatMessage("Will head to the Help Center if I have more questions tho", false),
//    ChatMessage("Boom!", true),
//    ChatMessage("Hmmm", false),
//    ChatMessage("I think I get it", false),
//    ChatMessage("Will head to the Help Center if I have more questions tho", false),
//    ChatMessage("Boom!", true),
//    ChatMessage("Hmmm", false),
//    ChatMessage("I think I get it", false),
//    ChatMessage("Will head to the Help Center if I have more questions tho", false)
//)
//
//@Composable
//fun MessageBubble(message: ChatMessage) {
//    val bubbleColor = if (message.isMine) Color(0xFF0B93F6) else Color(0xFFE5E5EA)
//    val textColor = if (message.isMine) Color(0xFFFFF8F0) else Color(0xFF202124)
//    Box(
//        modifier = Modifier
//            .fillMaxWidth(),
//        contentAlignment = if (message.isMine) Alignment.CenterEnd else Alignment.CenterStart
//    ) {
//        Text(
//            text = message.text,
//            color = textColor,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Normal,       // easy on the eyes
//            lineHeight = 29.sp,                   // gives enough spacing between lines
//            letterSpacing = 0.7.sp,               // subtle, professional
//            fontFamily = FontFamily.SansSerif,
//            modifier = Modifier
//                .background(bubbleColor, shape = RoundedCornerShape(16.dp))
//                .padding(11.dp)
////                .widthIn(max = 250.dp)
//                .fillMaxWidth(0.7f)
//        )
//    }
//}
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soumyadeep.pingme.model.Message
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MessageBubble(message: Message) {
    // determine whether this message was sent by the current user
    val currentUserPhone = FirebaseAuth.getInstance().currentUser?.phoneNumber
    val isMine = currentUserPhone != null && currentUserPhone == message.senderPhoneNumber

    val bubbleColor = if (isMine) Color(0xFF0B93F6) else Color(0xFFE5E5EA)
    val textColor = if (isMine) Color.White else Color(0xFF202124)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = if (isMine) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = bubbleColor,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isMine) 16.dp else 0.dp,
                        bottomEnd = if (isMine) 0.dp else 16.dp
                    )
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .widthIn(max = 260.dp) // caps max width for readability
        ) {
            Text(
                text = message.message,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                letterSpacing = 0.2.sp,
                fontFamily = FontFamily.SansSerif
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        val tsText = formatTimestampSafe(message.timeStamp)
        if (tsText.isNotEmpty()) {
            Text(
                text = tsText,
                fontSize = 11.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

/** Simple, non-@Composable formatter so we don't mix composable scopes. */
private fun formatTimestampSafe(timestamp: Long): String {
    if (timestamp <= 0L) return ""
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return try {
        sdf.format(Date(timestamp))
    } catch (e: Exception) {
        ""
    }
}
