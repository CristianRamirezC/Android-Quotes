package com.example.android_quotes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.android_quotes.viewModel.QuoteViewModel

@Composable
fun QuoteScreen(
    quoteViewModel: QuoteViewModel,
    modifier: Modifier = Modifier,
) {
    val quote: String by quoteViewModel.quote.observeAsState(initial = "\"It's not a bug, it's an undocumented feature\"")
    val author: String by quoteViewModel.author.observeAsState(initial = "unknown")
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
            color = Color.White,
            fontStyle = FontStyle.Italic
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