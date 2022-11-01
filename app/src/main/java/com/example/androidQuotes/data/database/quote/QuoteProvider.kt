package com.example.androidQuotes.data.database.quote

import com.example.androidQuotes.data.model.quote.QuoteModel

class QuoteProvider {
    companion object {
        var quotes: List<QuoteModel> = emptyList()
    }
}