package com.soumyadeep.pingme.sandbox

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

@Composable
fun TextFieldComparison() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. TextField
        TextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("TextField") }
        )

        // 2. OutlinedTextField
        OutlinedTextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text("OutlinedTextField") }
        )

        // 3. BasicTextField
        BasicTextField(
            value = text3,
            onValueChange = { text3 = it },
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(12.dp)
                .fillMaxWidth()
        )
        //2. Buttons
        Button(onClick = { }) {
            Text("Standard Button")
        }

        // 2. TextButton
        TextButton(onClick = { }) {
            Text("Text Button")
        }

        // 3. OutlinedButton
        OutlinedButton(onClick = { }) {
            Text("Outlined Button")
        }

        // 4. IconButton
        IconButton(onClick = { }) {
            Icon(Icons.Default.Favorite, contentDescription = "Like")
        }

        // 5. FloatingActionButton (FAB)
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }

        // 6. ClickableText
        ClickableText(
            text = AnnotatedString("Clickable Text (like a link)"),
            onClick = { offset -> println("Clicked at $offset") }
        )
        LazyRow {
            items(10) { index ->
                Text("Item $index", modifier = Modifier.padding(8.dp))
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(5) { index ->
                Text("Item $index", modifier = Modifier.padding(8.dp))
            }
        }

    }
    }

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TextFieldComparisonPreview() {
    TextFieldComparison()
}