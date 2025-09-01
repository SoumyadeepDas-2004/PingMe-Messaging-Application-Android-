package com.soumyadeep.pingme.sandbox

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComparisonScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Drawer + Scaffold = app structure
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer Item 1", modifier = Modifier.padding(16.dp))
                Text("Drawer Item 2", modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Top Bar") }) },
            bottomBar = { BottomAppBar { Text("Bottom Bar") } },
            floatingActionButton = {
                FloatingActionButton(onClick = { scope.launch { drawerState.open() } }) {
                    Icon(Icons.Default.Add, contentDescription = "FAB")
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    // Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Text("This is inside a Card", modifier = Modifier.padding(16.dp))
                    }
                }
                item {
                    // Surface
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shadowElevation = 4.dp,
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text("This is a Surface", modifier = Modifier.padding(16.dp))
                    }
                }
                item {
                    // Chip
                    AssistChip(onClick = {}, label = { Text("Chip Example") })
                }
                item {
                    // Divider
                    Divider(thickness = 2.dp)
                }
                item {
                    // Progress Indicators
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp) // make it bigger
                            .padding(8.dp),
                        color = MaterialTheme.colorScheme.primary, // optional
                        strokeWidth = 4.dp // thickness
                    )
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(progress = 0.6f)
                }
                item {
                    // Snackbar button
                    Button(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("This is a Snackbar")
                        }
                    }) {
                        Text("Show Snackbar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComparisonPreview() {
    ComparisonScreen()
}
