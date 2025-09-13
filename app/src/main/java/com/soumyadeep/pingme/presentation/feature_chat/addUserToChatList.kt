package com.soumyadeep.pingme.presentation.feature_chat

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soumyadeep.pingme.presentation.viewmodel.BaseViewModel
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import com.soumyadeep.pingme.common.AppTopBar

@Composable
fun AddFriendScreen(navController: NavHostController, viewModel: BaseViewModel = hiltViewModel()) {
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Add User",
                showBack = true,
                onBackClick = { navController.popBackStack() },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Enter Phone Number") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.addUserToChatList(phoneNumber) { success ->
                        if (success) {
                            Toast.makeText(
                                context,
                                "User added! You can now chat.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "User not found.", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
//            modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Friend")
            }
        }
    }
}