package com.soumyadeep.pingme.presentation.feature_contact.contact

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
import androidx.compose.ui.unit.dp
import com.soumyadeep.pingme.presentation.ui.components.chatlistscreen.BottomBarTab
import com.soumyadeep.pingme.presentation.ui.components.chatlistscreen.ReusableBottomBar
import com.soumyadeep.pingme.presentation.ui.components.contactscreen.AppTopBar
import androidx.navigation.NavHostController
import  androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.foundation.layout.Column
import com.soumyadeep.pingme.common.AppTopBar
//@Composable
//fun Contacts(navController: NavHostController) {
//
////    Scaffold(
////        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
////        topBar = {
//            AppTopBar(
//            title = "Contacts",
//            onSearchClick = { /* Open search UI */ })
////        )},
////        bottomBar = {
////            ReusableBottomBar(
////                currentTab = BottomBarTab.Contacts, // Pre-selected tab
////                onTabSelected = { /* do nothing for now */ }
////            )
////        },
//
////    ) { innerPadding ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.background)
////                .padding(innerPadding)
//        ) {
//            items(contacts) { contact ->
//                ContactItem(contact)
//
//            }
//        }
//    }
////}
@Composable
fun Contacts(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppTopBar(
            title = "Contacts",
            showSearch = true,
            onSearchClick = { /* search action */ }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(contacts) { contact ->
                ContactItem(contact)
            }
        }
    }
}