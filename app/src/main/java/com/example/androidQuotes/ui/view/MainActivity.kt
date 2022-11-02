package com.example.androidQuotes.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.androidQuotes.ui.view.quote.QuoteScreen
import com.example.androidQuotes.ui.view.theme.AndroidQuotesTheme
import com.example.androidQuotes.ui.viewModel.quote.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quoteViewModel.onCreate()
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
