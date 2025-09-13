package com.soumyadeep.pingme.presentation.ui.components.callscreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// class of tabs of bottom bar
sealed class BottomBarTab(val title: String, val icon: ImageVector) {
    object Contacts : BottomBarTab("Contacts", Icons.Default.Person)
    object Calls : BottomBarTab("Calls", Icons.Default.Call)
    object Chat : BottomBarTab("Chat", Icons.Default.Message)
    object Settings : BottomBarTab("Settings", Icons.Default.Settings)
}

@Composable
fun ReusableBottomBar(
    currentTab: BottomBarTab,
    onTabSelected: (BottomBarTab) -> Unit
) {
    // objects of class BottomBarTab
    val tabs = listOf(
        BottomBarTab.Contacts,
        BottomBarTab.Calls,
        BottomBarTab.Chat,
        BottomBarTab.Settings
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        tabs.forEach { tab ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) { onTabSelected(tab) },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.offset(
                        y = if (currentTab == tab) (-7).dp else 0.dp
                    )
                ) {
                    Icon(
                        tab.icon,
                        contentDescription = tab.title,
                        tint = if (currentTab == tab)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        tab.title,
                        fontSize = 14.sp,
                        color = if (currentTab == tab)
                            MaterialTheme.colorScheme.onBackground
                        else
                            MaterialTheme.colorScheme.onBackground,
                        fontWeight = if (currentTab == tab)
                            FontWeight.Bold
                        else
                            FontWeight.SemiBold
                    )
                }
            }
        }
    }
}


