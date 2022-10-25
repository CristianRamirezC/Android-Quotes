package com.example.android_quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.android_quotes.ui.theme.AndroidQuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    QuoteScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun QuoteScreen(
    modifier: Modifier = Modifier,
    quote: String = "\"This is a quote\"",
    author: String = "Author"
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF4F1497)),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = quote,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier,
            text = author,
            textAlign = TextAlign.End,
            fontSize = 25.sp,
            color = Color.White

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidQuotesTheme {
        Greeting("Android")
    }
}