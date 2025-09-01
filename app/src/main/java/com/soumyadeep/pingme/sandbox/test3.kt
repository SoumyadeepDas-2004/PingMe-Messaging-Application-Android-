package com.soumyadeep.pingme.sandbox

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent() {
    Column(Modifier.padding(16.dp)) {
        Text("Home", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(8.dp))
        Text("Profile", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(8.dp))
        Text("Settings", style = MaterialTheme.typography.bodyLarge)
    }
}

/* ----------------------------- 1. Modal Drawer ----------------------------- */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ModalDrawerExample() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet { DrawerContent() }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Modal Drawer") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Box(Modifier.padding(padding)) {
                Text("Main Content")
            }
        }
    }
}

/* --------------------------- 2. Dismissible Drawer -------------------------- */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DismissibleDrawerExample() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    DismissibleNavigationDrawer(
        drawerContent = {
            DismissibleDrawerSheet { DrawerContent() }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Dismissible Drawer") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Box(Modifier.padding(padding)) {
                Text("Main Content")
            }
        }
    }
}

/* --------------------------- 3. Permanent Drawer --------------------------- */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PermanentDrawerExample() {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet { DrawerContent() }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Permanent Drawer") })
            }
        ) { padding ->
            Box(Modifier.padding(padding)) {
                Text("Main Content")
            }
        }
    }
}

/* ---------------------------- 🔍 Previews ---------------------------------- */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ModalDrawerPreview() {
    ModalDrawerExample()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DismissibleDrawerPreview() {
    DismissibleDrawerExample()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PermanentDrawerPreview() {
    PermanentDrawerExample()
}
//The most commonly used drawer type in modern apps is:
//
//✅ ModalNavigationDrawer