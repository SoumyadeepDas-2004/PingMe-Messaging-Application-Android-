package com.soumyadeep.pingme.presentation.feature_chat.chatlist

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.presentation.viewmodel.BaseViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import android.net.Uri
@Composable

fun ChatList(
    navController: NavHostController,
    baseViewModel: BaseViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val chatList by baseViewModel.chatList.collectAsState(initial = emptyList())
    val activity = LocalContext.current as? Activity

    BackHandler {
        activity?.moveTaskToBack(true) // Sends app to background, preserves state
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            com.soumyadeep.pingme.common.AppTopBar(
                title = "Chats",
                showSearch = true,
                showAdd=true,
                onAddClick={navController.navigate("add_friend")},
                onSearchClick = {}
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            items(chatList, key = { it.userId }) { chat ->
                ChatItem(
                    chat = chat,
                    onClick = {
                        val encodedPhone = Uri.encode(chat.phoneNumber, null)
                        navController.navigate("chat/$encodedPhone")
                    }
                )
            }
        }
    }
}
