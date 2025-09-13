package com.soumyadeep.pingme.presentation.ui.components.chatscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.BlendMode
//import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soumyadeep.pingme.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topappbar() {
//    val topBarColor = if (isSystemInDarkTheme()) ChatTopBarDark else ChatTopBarLight
    TopAppBar(
//        modifier = Modifier.background(Color.Red),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile",
//                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface, blendMode = BlendMode.SrcIn),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.size(8.dp))
                Column {
                    Text("Soumyadeep Das", fontWeight = FontWeight.Bold,color=MaterialTheme.colorScheme.onSurface)
                    Text("Active 11m ago", fontSize = 12.sp,color=MaterialTheme.colorScheme.onSurface)
                }
            }
        },
        // left side elements
        navigationIcon = {
            Row()
            {
                IconButton(onClick = { /* TODO: Back navigation */ }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }


            }

        },
        // right side elements
        actions = {
            IconButton(onClick = { /* Call */ }) {
                Icon(
                    Icons.Default.Call,
                    contentDescription = "Call",
                    tint =MaterialTheme.colorScheme.onSurface// ✅ This is how you tint an Icon
                )
            }
            IconButton(onClick = { /* Call */ }) {
                Icon(
                    Icons.Default.Videocam,
                    contentDescription = "videocall",
                    tint = MaterialTheme.colorScheme.onSurface// ✅ This is how you tint an Icon
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface)
    )
}