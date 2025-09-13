package com.soumyadeep.pingme.presentation.feature_call.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soumyadeep.pingme.presentation.ui.components.callscreen.ReusableBottomBar
import com.soumyadeep.pingme.presentation.ui.components.callscreen.AppTopBar
import com.soumyadeep.pingme.presentation.ui.components.callscreen.BottomBarTab
import androidx.compose.foundation.layout.Arrangement
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.common.AppTopBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calls(navController: NavHostController) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            AppTopBar(
                title = "Calls",
                showSearch = true,
                onSearchClick = { /* search action */ }
            )
        },
//        bottomBar = {
//            ReusableBottomBar(
//                currentTab = BottomBarTab.Calls, // Pre-selected tab
//                onTabSelected = { /* do nothing for now */ }
//            )
//        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(sampleCallLogs) { call ->
                CallItem(call)
            }
        }
    }
}


