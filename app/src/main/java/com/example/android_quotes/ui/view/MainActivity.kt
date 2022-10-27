package com.example.android_quotes.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.android_quotes.view.QuoteScreen
import com.example.android_quotes.ui.view.theme.AndroidQuotesTheme
import com.example.android_quotes.ui.viewModel.quote.QuoteViewModel

class MainActivity : ComponentActivity() {
    private val quoteViewModel: QuoteViewModel = QuoteViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {

        quoteViewModel.onCreate()
        super.onCreate(savedInstanceState)
        setContent {
            AndroidQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    QuoteScreen(quoteViewModel = quoteViewModel)
                }
            }
        }
    }
}
