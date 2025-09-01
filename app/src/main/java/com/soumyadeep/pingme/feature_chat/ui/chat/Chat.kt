package com.soumyadeep.pingme.feature_chat.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soumyadeep.pingme.R
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


//import com.soumyadeep.pingme.feature_chat.ui.personalchat.PChat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat() {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 50.dp)

    ) {
        // TOP BAR
        TopAppBar(
            title = {
                Column {
                    Text(
                        "Helena Hills",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Title text color
                    )
                    Text(
                        "Active 11m ago",
                        fontSize = 12.sp,
                        color = Color.Black // Subtitle text color
                    )
                }
            },
            navigationIcon = {
//                IconButton(onClick = { /* TODO: Back navigation */ }) {
//                    Icon(
//                        Icons.Default.ArrowBack,
//                        contentDescription = "Back",
//                        tint = Color.Black
//                    )
//                }
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                    // If you want tint here:
                    // colorFilter = ColorFilter.tint(Color.Black)
                )
            },
            actions = {
                IconButton(onClick = { /* Call */ }) {
                    Icon(
                        Icons.Default.Call,
                        contentDescription = "Call",
                        tint = Color.Black // ✅ This is how you tint an Icon
                    )
                }
                IconButton(
                    onClick = { /* Handle video call click */ }
                ) {
                    Image(
                        painter = painterResource(R.drawable.videocall),
                        contentDescription = "Video",
                        modifier = Modifier
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black) // ✅ This is how you tint an Image
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFE6F7F2)
            )
        )

        HorizontalDivider()
//        Column(modifier=Modifier.padding(vertical = 10.dp))
//        {
        Spacer(Modifier.height(8.dp))
//        Box(
//            modifier = Modifier.padding(horizontal = 10.dp)
//        )
//        {

            // DATE
            Text(
                text = "Nov 30, 2023, 9:41 AM",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(8.dp))

            // MESSAGES
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp) ,// ⬅️ Add horizontal padding here
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
                MessageBubble("This is the main chat template", isMine = true)
                MessageBubble("Oh?", isMine = false)
                MessageBubble("Cool", isMine = false)
                MessageBubble("How does it work?", isMine = false)
                MessageBubble(
                    "You just edit any text to type in the conversation you want to show, and delete any bubbles you don’t want to use",
                    isMine = true
                )
                MessageBubble("Boom!", isMine = true)
                MessageBubble("Hmmm", isMine = false)
                MessageBubble("I think I get it", isMine = false)
                MessageBubble(
                    "Will head to the Help Center if I have more questions tho",
                    isMine = false
                )
//            }

        }// INPUT BAR
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // ⬅️ Set your desired height
                    .background(Color.White, shape = RoundedCornerShape(50))
                    .padding(horizontal = 16.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(50))
            )  {
                BasicTextField(
                    value = message,
                    onValueChange = { message = it },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 18.sp // 👈 Make your message text bigger
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 22.dp, vertical = 8.dp) // ✅ Padding INSIDE
                        ) {
                            if (message.isEmpty()) {
                                Text("Message...", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier.weight(1f)
                )


                Image(
                    painter = painterResource(R.drawable.mic), // your mic icon
                    contentDescription = "Mic",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape) // ✅ Makes it rounded
                        .background(color = Color.Transparent
                ))
                Spacer(Modifier.width(12.dp))
                Image(
                    painter = painterResource(R.drawable.emoji), // your emoji icon
                    contentDescription = "Emoji",

                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.files), // your attach icon
                    contentDescription = "Attach",

                    modifier = Modifier.size(40.dp)
                )

            }
        }
    }
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(50))
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//        ) {
//            Text(
//                text = "Message...",
//                color = Color.Gray,
//                modifier = Modifier.weight(1f)
//            )
////            Icon(painter = painterResource(R.drawable.emoji), contentDescription = "Emoji")
////            Spacer(Modifier.width(8.dp))
////            Icon(painter = painterResource(R.drawable.mic), contentDescription = "Mic")
////            Spacer(Modifier.width(8.dp))
////            Icon(painter = painterResource(R.drawable.files), contentDescription = "Image")
//        }
}


@Composable
fun MessageBubble(message: String, isMine: Boolean) {
    val bubbleColor = if (isMine) Color.Black else Color(0xFFE0E0E0)
    val textColor = if (isMine) Color.White else Color.Black
    val alignment = if (isMine) Alignment.End else Alignment.Start

    Box(
        modifier = Modifier
            .fillMaxWidth(),

        contentAlignment = if (isMine) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Text(
            text = message,
            color = textColor,
            modifier = Modifier
                .background(bubbleColor, shape = RoundedCornerShape(16.dp))
                .padding(12.dp)
                .widthIn(max = 250.dp)
        )
    }
}

