package com.example.androidQuotes.data.database.quote

import com.example.androidQuotes.data.model.quote.QuoteModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteProvider @Inject constructor() {
    var quotes: List<QuoteModel> = emptyList()
}