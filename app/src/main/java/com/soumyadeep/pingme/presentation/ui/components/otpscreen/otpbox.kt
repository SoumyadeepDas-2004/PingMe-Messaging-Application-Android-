//package com.soumyadeep.pingme.presentation.ui.components.otpscreen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import kotlin.text.compareTo
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OtpInput(
//    otpLength: Int = 4,
//    onOtpEntered: (String) -> Unit
//) {
//    var otpValue by remember { mutableStateOf("") }
//    val focusRequester = remember { FocusRequester() }
//    val focusManager = LocalFocusManager.current
//
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//    ) {
//        repeat(otpLength) { index ->
//            val isSelected = index == otpValue.length
//            Box(
//                modifier = Modifier
//                    .width(56.dp)
//                    .height(56.dp)
//                    .background(
//                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
//                        shape = RoundedCornerShape(8.dp)
//                    )
//                    .then(
//                        if (isSelected) Modifier.border(
//                            1.dp,
//                            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
//                            RoundedCornerShape(8.dp)
//                        ) else Modifier
//                    )
//                    .clickable {
//                        focusManager.clearFocus(force = true)
//                        focusRequester.requestFocus()
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = otpValue.getOrNull(index)?.toString() ?: "_",
//                    fontSize = 30.sp,
//                    color = MaterialTheme.colorScheme.primary,
//                )
//            }
//        }
//    }
//    BasicTextField(
//        value = otpValue,
//        onValueChange = {
//            if (it.length <= otpLength && it.all { char -> char.isDigit() }) {
//                otpValue = it
//                if (it.length == otpLength) {
//                    onOtpEntered(it)
//                    focusManager.clearFocus(force = true)
//                }
//            }
//        },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//        cursorBrush = SolidColor(Color.Transparent), // ✅ Hides the real cursor
//        modifier = Modifier
//            .focusRequester(focusRequester)
//            .width(1.dp)
//            .height(1.dp),
//        singleLine = true
//    )
//}
package com.soumyadeep.pingme.presentation.ui.components.otpscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpInput(
    otpLength: Int = 6,
    onOtpEntered: (String) -> Unit
) {
    var otpValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.toFloat() // ✅ Float
    val spacing = 8f

    val totalSpacing = spacing * (otpLength - 1)
    val boxSize = ((screenWidthDp - totalSpacing) / otpLength).dp

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(otpLength) { index ->
            val isSelected = index == otpValue.length
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f) // makes it square
                    .background(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .then(
                        if (isSelected) Modifier.border(
                            1.dp,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            RoundedCornerShape(8.dp)
                        ) else Modifier
                    )
                    .clickable {
                        focusManager.clearFocus(force = true)
                        focusRequester.requestFocus()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = otpValue.getOrNull(index)?.toString() ?: "_",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }

    BasicTextField(
        value = otpValue,
        onValueChange = {
            if (it.length <= otpLength && it.all { char -> char.isDigit() }) {
                otpValue = it
                if (it.length == otpLength) {
                    onOtpEntered(it)
                    focusManager.clearFocus(force = true)
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        cursorBrush = SolidColor(Color.Transparent),
        modifier = Modifier
            .focusRequester(focusRequester)
            .width(1.dp)
            .height(1.dp),
        singleLine = true
    )
}
