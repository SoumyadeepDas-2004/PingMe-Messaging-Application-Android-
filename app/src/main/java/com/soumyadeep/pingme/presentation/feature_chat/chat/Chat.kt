//package com.soumyadeep.pingme.presentation.feature_chat.chat
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.soumyadeep.pingme.presentation.feature_chat.chat.bottomappbar
//import com.soumyadeep.pingme.common.AppTopBar
//
////import androidx.compose.foundation.layout.WindowInsets
////import androidx.compose.foundation.layout.safeDrawing
//import androidx.navigation.NavHostController
//import androidx.compose.foundation.lazy.rememberLazyListState
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Chat(chatId: String,navController: NavHostController) {
////    val chatBackground = if (isSystemInDarkTheme()) Color(0xFF121212) else Color(0xFFFAFAFA)
//    val listState = rememberLazyListState()
//    Scaffold(
//        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
//        topBar = { AppTopBar(
//            title = null,
//            showBack = true,
//            onBackClick = { navController.popBackStack() },
//        ) },
//        bottomBar = {
//            bottomappbar(
//                onSend = { text ->
//                    // Handle sending the message here
//                    println("Message sent: $text")
//                    // e.g. update ViewModel or message list
//                },
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.background)
//                .padding(innerPadding)
//
//        ) {
////            Text(
////                text = "Nov 30, 2023, 9:41 AM",
////                color = Color.Gray,
////                fontSize = 12.sp,
////                modifier = Modifier.align(Alignment.CenterHorizontally)
////            )
////
////            Spacer(Modifier.height(8.dp))
//
//            LazyColumn(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(horizontal = 12.dp),
//                verticalArrangement = Arrangement.spacedBy(4.dp),
//                state = listState,
//                reverseLayout = true
//
//            ) {
//                items(dummyMessages) { message ->
//                    MessageBubble(message)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(5.dp))
//        }
//    }
//}
//
package com.soumyadeep.pingme.presentation.feature_chat.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.presentation.feature_chat.chat.bottomappbar
import com.soumyadeep.pingme.common.AppTopBar
import com.soumyadeep.pingme.presentation.viewmodel.BaseViewModel
import com.soumyadeep.pingme.model.Message
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat(
    otherUserPhone: String,
    navController: NavHostController,
    baseViewModel: BaseViewModel = viewModel()
) {
    val listState = rememberLazyListState()
    val currentUserPhone = FirebaseAuth.getInstance().currentUser?.phoneNumber
    if (currentUserPhone.isNullOrBlank()) {
        LaunchedEffect(Unit) { navController.popBackStack() }
        return
    }

    // Local message list
    val messagesState = remember { mutableStateListOf<Message>() }

    // 🔌 Attach listener for real-time messages
    baseViewModel.listenForConversation(currentUserPhone, otherUserPhone) { newMessage ->
        messagesState.apply {
            add(newMessage)
            sortByDescending { it.timeStamp }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            AppTopBar(
                title = otherUserPhone, // you can replace with name if you fetch it
                showBack = true,
                onBackClick = { navController.popBackStack() },
            )
        },
        bottomBar = {
            bottomappbar(
                onSend = { text ->
                    if (text.isNotBlank()) {
                        baseViewModel.sendMessage(
                            senderPhoneNumber = currentUserPhone,
                            receiverPhoneNumber = otherUserPhone,
                            messageText = text
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                state = listState,
                reverseLayout = true
            ) {
                items(messagesState) { message ->
                    MessageBubble(message)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}