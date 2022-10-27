package com.example.android_quotes.data.database.quote

import com.example.android_quotes.data.model.quote.QuoteModel

class QuoteProvider {
    companion object {
        var quotes: List<QuoteModel> = emptyList()
    }
}