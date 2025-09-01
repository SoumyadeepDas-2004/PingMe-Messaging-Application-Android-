package com.soumyadeep.pingme.sandbox

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun demo1() {
    Column(modifier = Modifier.background(Color.Yellow)) // column without maxsize
    {
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun demo1Preview() {
    demo1()
}

@Composable
fun demo2() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow))//column with maxsize
    {
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun demo2Preview() {
    demo2()
}

@Composable
fun demo3() {
    Row(modifier = Modifier.background(Color.Yellow))//row without maxsize
    {
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun demo3Preview() {
    demo3()
}

@Composable
fun demo4() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )//row with maxsize
    {
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun demo4Preview() {
    demo4()
}

@Composable
fun demo5() {
    Box(modifier = Modifier.background(Color.Yellow))//box without maxsize
    {
        Text("Hi seoumyadeep")// all soumyadeep stacked into each other
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
        Text("Hi seoumyadeep")
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun demo5Preview() {
    demo5()
}

@Composable
fun demo6() {
    Column(modifier = Modifier.fillMaxSize())
    {
        Box(modifier = Modifier

            .weight(0.65f) // take 50% height when there is another components
            .background(Color.Yellow))//box shapes
        {
            Text("Box width ends here",color=Color.Red, fontSize = 30.sp)

        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun demo6Preview() {
    demo6()
}