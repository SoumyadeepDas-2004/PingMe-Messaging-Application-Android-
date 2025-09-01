package com.soumyadeep.pingme.sandbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle

@Composable
fun GradientDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // 1️⃣ Solid Color (Brush.SolidColor)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(brush = SolidColor(Color.Magenta)),
            contentAlignment = Alignment.Center
        ) {
            Text("SolidColor", color = Color.White)
        }

        // 2️⃣ Linear Gradient (Left to Right)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Blue),
                        start = Offset(0f, 0f),
                        end = Offset(500f, 0f) // left → right
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("Linear Gradient", color = Color.White)
        }

        // 3️⃣ Radial Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.Yellow, Color.Red),
                        center = Offset(200f, 100f),
                        radius = 300f
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("Radial Gradient", color = Color.Black)
        }

        // 4️⃣ Sweep Gradient
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            Color.Red,
                            Color.Green,
                            Color.Blue,
                            Color.Cyan,
                            Color.Magenta,
                            Color.Red
                        )
                    ),
                    shape = RoundedCornerShape(75.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("Sweep", color = Color.White)
        }

        // 5️⃣ Gradient Text
        Text(
            text = "Gradient Text",
            fontSize = 28.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    listOf(Color.Magenta, Color.Cyan, Color.Blue)
                )
            )
        )

        // 6️⃣ Gradient Border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(
                    width = 6.dp,
                    brush = Brush.linearGradient(
                        listOf(Color.Red, Color.Yellow, Color.Green)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("Gradient Border", fontSize = 18.sp)
        }

        // 7️⃣ Gradient on Canvas Shape
        Canvas(modifier = Modifier.size(150.dp)) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Cyan, Color.Blue, Color.Black),
                    center = center,
                    radius = size.minDimension / 2
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GradientDemoPreview() {
    GradientDemo()
}
