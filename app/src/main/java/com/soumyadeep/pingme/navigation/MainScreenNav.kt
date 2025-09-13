package com.soumyadeep.pingme.navigation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.soumyadeep.pingme.common.BottomBarTab
import com.soumyadeep.pingme.common.ReusableBottomBar
import com.soumyadeep.pingme.presentation.feature_call.call.Calls
import com.soumyadeep.pingme.presentation.feature_chat.chat.Chat
import com.soumyadeep.pingme.presentation.feature_chat.chatlist.ChatList
import com.soumyadeep.pingme.presentation.feature_chat.AddFriendScreen
import com.soumyadeep.pingme.presentation.feature_contact.contact.Contacts
import com.soumyadeep.pingme.presentation.feature_profile.editprofile.EditProfile
import com.soumyadeep.pingme.presentation.feature_setting.setting.Settings
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import android.net.http.SslCertificate.saveState
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.soumyadeep.pingme.common.ReusableBottomBar
import com.soumyadeep.pingme.presentation.feature_call.call.Calls
import com.soumyadeep.pingme.presentation.feature_chat.chatlist.ChatList
import com.soumyadeep.pingme.presentation.feature_contact.contact.Contacts
import com.soumyadeep.pingme.presentation.feature_setting.setting.Settings
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.*
fun MainScreenNav(rootNavController: NavHostController) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            ReusableBottomBar(
                currentTab = when (pagerState.currentPage) {
                    0 -> BottomBarTab.Chat
                    1 -> BottomBarTab.Contacts
                    2 -> BottomBarTab.Calls
                    3 -> BottomBarTab.Settings
                    else -> BottomBarTab.Chat
                },
                onTabSelected = { tab ->
                    val targetPage = when (tab) {
                        BottomBarTab.Chat -> 0
                        BottomBarTab.Contacts -> 1
                        BottomBarTab.Calls -> 2
                        BottomBarTab.Settings -> 3
                    }
                    coroutineScope.launch { pagerState.animateScrollToPage(targetPage) }
                }
            )
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                // apply **only bottom padding**, ignore top
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) { page ->
            when (page) {
                0 -> ChatList(rootNavController)
                1 -> Contacts(rootNavController)
                2 -> Calls(rootNavController)
                3 -> Settings(rootNavController)
            }
        }
    }
}
//@OptIn(ExperimentalAnimationApi::class)
//fun MainScreenNav(rootNavController: NavHostController) {
//    val bottomNavController = rememberNavController()
//    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    fun routeToTab(route: String?): BottomBarTab {
//        return when {
//            route?.startsWith("contacts") == true -> BottomBarTab.Contacts
//            route?.startsWith("calls") == true -> BottomBarTab.Calls
//            route?.startsWith("chatlist") == true -> BottomBarTab.Chat
//            route?.startsWith("settings") == true -> BottomBarTab.Settings
//            else -> BottomBarTab.Chat
//        }
//    }
//
//    val showBottomBar = currentRoute != null &&
//            !currentRoute.startsWith("chat/") && // 🚫 hide in chat detail
//            currentRoute != "edit_profile"        // (optional) hide on edit profile too
//
//    Scaffold(
//        bottomBar = {
//            if (showBottomBar) {
//                val selectedTab = routeToTab(currentRoute)
//                ReusableBottomBar(
//                    currentTab = selectedTab,
//                    onTabSelected = { selectedTab ->
//                        val targetRoute = when (selectedTab) {
//                            BottomBarTab.Calls -> "calls"
//                            BottomBarTab.Chat -> "chatlist"
//                            BottomBarTab.Contacts -> "contacts"
//                            BottomBarTab.Settings -> "settings"
//                        }
//                        if (currentRoute != targetRoute) {
//                            bottomNavController.navigate(targetRoute) {
//                                popUpTo(bottomNavController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    }
//                )
//            }
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController = bottomNavController,
//            startDestination = "chatlist",
//            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
//        ) {
//            composable("calls") { Calls(bottomNavController) }
////            composable("chatlist") { ChatList(navController = rootNavController) }
//            composable("chatlist") { ChatList(bottomNavController) }
//            composable("contacts") { Contacts(bottomNavController) }
//            composable("settings") { Settings(bottomNavController) }
//            composable("edit_profile") { EditProfile(bottomNavController) }
//            composable("add_friend") {
//                AddFriendScreen(navController = bottomNavController)
//            }
//            // ✅ Chat detail (full screen, no bottom bar)
//            composable("chat/{otherUserPhone}") { backStackEntry ->
//                val otherUserPhone = backStackEntry.arguments
//                    ?.getString("otherUserPhone")
//                    ?.let { Uri.decode(it) }
//
//                if (otherUserPhone.isNullOrBlank()) {
//                    // show fallback UI or navigate back
//                    LaunchedEffect(Unit) { bottomNavController.popBackStack() }
//                    return@composable
//                }
//
//                Chat(otherUserPhone = otherUserPhone, navController = bottomNavController)
//            }
//        }
//    }
//}



//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun MainScreenNav(rootNavController: NavHostController) {
//    val navController = rememberNavController()
//    val pagerState = rememberPagerState(initialPage = 0)
//    val coroutineScope = rememberCoroutineScope()
//
//    // Top-level tabs
//    val tabs = listOf("chatlist", "contacts", "calls", "settings")
//
//    // Helper functions
//    fun routeToPage(route: String?) = tabs.indexOf(route).coerceAtLeast(0)
//    fun pageToRoute(page: Int) = tabs.getOrElse(page) { "chatlist" }
//
//    // Observe current route
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    // Sync NavController when Pager changes
//    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
//        if (!pagerState.isScrollInProgress) {
//            val targetRoute = pageToRoute(pagerState.currentPage)
//            if (currentRoute != targetRoute) {
//                navController.navigate(targetRoute) {
//                    popUpTo(navController.graph.findStartDestination().id) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
//        }
//    }
//
//    // Sync Pager when NavController changes
//    LaunchedEffect(currentRoute) {
//        val targetPage = routeToPage(currentRoute)
//        if (pagerState.currentPage != targetPage) {
//            pagerState.animateScrollToPage(targetPage)
//        }
//    }
//
//    Scaffold(
//        bottomBar = {
//            val showBottomBar = currentRoute in tabs
//            if (showBottomBar) {
//                ReusableBottomBar(
//                    currentTab = when (pagerState.currentPage) {
//                        0 -> BottomBarTab.Chat
//                        1 -> BottomBarTab.Contacts
//                        2 -> BottomBarTab.Calls
//                        3 -> BottomBarTab.Settings
//                        else -> BottomBarTab.Chat
//                    },
//                    onTabSelected = { tab ->
//                        val page = when (tab) {
//                            BottomBarTab.Chat -> 0
//                            BottomBarTab.Contacts -> 1
//                            BottomBarTab.Calls -> 2
//                            BottomBarTab.Settings -> 3
//                        }
//                        coroutineScope.launch {
//                            pagerState.animateScrollToPage(page)
//                        }
//                    }
//                )
//            }
//        }
//    ) { innerPadding ->
//
//        NavHost(
//            navController = navController,
//            startDestination = "chatlist",
//            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
//        ) {
//            // Top-level tabs
//            composable("chatlist") {
//                HorizontalPager(
//                    pageCount = 1,
//                    state = pagerState,
//                    modifier = Modifier.fillMaxSize(),
//                    userScrollEnabled = false // Pager only for future extension if needed
//                ) { ChatList(rootNavController) }
//            }
//            composable("contacts") { Contacts(rootNavController) }
//            composable("calls") { Calls(rootNavController) }
//            composable("settings") { Settings(rootNavController) }
//
//            // Deep navigation screens
//            composable("edit_profile") { EditProfile(navController) }
//            composable("add_friend") { AddFriendScreen(navController) }
//            composable("chat/{otherUserPhone}") { backStackEntry ->
//                val phone = backStackEntry.arguments?.getString("otherUserPhone")?.let { Uri.decode(it) }
//                if (phone.isNullOrBlank()) {
//                    LaunchedEffect(Unit) { navController.popBackStack() }
//                    return@composable
//                }
//                Chat(otherUserPhone = phone, navController)
//            }
//        }
//    }
//}
