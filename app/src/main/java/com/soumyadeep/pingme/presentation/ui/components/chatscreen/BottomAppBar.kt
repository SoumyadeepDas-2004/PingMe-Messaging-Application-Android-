package com.soumyadeep.pingme.presentation.ui.components.chatscreen

//import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.InsertPhoto
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun bottomappbar( onSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                WindowInsets.navigationBars
                    .union(WindowInsets.ime)
                    .only(WindowInsetsSides.Bottom)
                    .asPaddingValues()
            )

    )

    {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.99f) //  width of textfield
                .align(Alignment.Center) // centered textfield
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
//                    .height(50.dp) // ⬅️ Set your desired height
                    .defaultMinSize(minHeight = 50.dp)
                    .padding(horizontal = 8.dp)
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(50))
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        RoundedCornerShape(50)
                    )

            ) {
                BasicTextField(
                    value = message,
                    onValueChange = { message = it },
                    singleLine = false,              // ✅ allow multiple lines
                    maxLines = 4,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal,       // easy on the eyes
                        lineHeight = 29.sp,                   // gives enough spacing between lines
                        letterSpacing = 0.7.sp,               // subtle, professional
                        fontFamily = FontFamily.SansSerif

                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .padding(
                                    horizontal = 22.dp,
                                    vertical = 8.dp
                                ) // ✅ Padding INSIDE

                        ) {
                            if (message.isEmpty()) {
                                Text(
                                    "Message...",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Send // ✅ show "Send" in soft keyboard
                    ),
                )

                IconButton(
                    onClick = { /* Call */ },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        Icons.Default.EmojiEmotions,
                        contentDescription = "Choose Emoji",
                        tint = MaterialTheme.colorScheme.onSurface// ✅ This is how you tint an Icon
                    )
                }

                IconButton(onClick = { /* Call */ }, modifier = Modifier.size(48.dp)) {
                    Icon(
                        Icons.Default.InsertPhoto,
                        contentDescription = "Attach File",
                        tint =MaterialTheme.colorScheme.onSurface// ✅ This is how you tint an Icon
                    )
                }
                if (message.isNotBlank()) {
                    IconButton(onClick = {
                        // send logic here
                        onSend(message)
                        message = ""
                    }, ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Send message",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    IconButton(onClick = { /* Voice message */ }, modifier = Modifier.size(48.dp)) {
                        Icon(
                            Icons.Default.Mic,
                            contentDescription = "Voice Message",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }


            }
        }
    }
}