package com.example.androidQuotes.ui.view.quote

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidQuotes.ui.viewModel.quote.QuoteViewModel

@Composable
fun QuoteScreen(
    quoteViewModel: QuoteViewModel,
    modifier: Modifier = Modifier,
) {
    val quote: String by quoteViewModel.quote.observeAsState(initial = "")
    val author: String by quoteViewModel.author.observeAsState(initial = "")
    val isLoading: Boolean by quoteViewModel.isLoading.observeAsState(initial = false)
    val isErrorGettingQuotes by quoteViewModel.isErrorGettingQuotes.observeAsState(initial = false)
    val context: Context = LocalContext.current

    if (isErrorGettingQuotes) {
        Toast.makeText(context, "Error getting Quotes", Toast.LENGTH_SHORT).show()
    }
    if (isLoading || isErrorGettingQuotes) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF201F1F))
                .padding(20.dp)
        ) {
            if (isErrorGettingQuotes) {
                val errorMessage = "Oops! An error has occurred retrieving quotes"
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = Color.White
                )
            } else if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color(0xFF1A247C))
                .clickable {
                    quoteViewModel.randomQuote()
                }
                .padding(20.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "\"$quote \"",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = author,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}