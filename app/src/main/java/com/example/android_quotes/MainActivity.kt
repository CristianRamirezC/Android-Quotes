package com.example.android_quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.android_quotes.ui.QuoteScreen
import com.example.android_quotes.ui.theme.AndroidQuotesTheme
import com.example.android_quotes.viewModel.QuoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    QuoteScreen(quoteViewModel = QuoteViewModel())
                }
            }
        }
    }
}
