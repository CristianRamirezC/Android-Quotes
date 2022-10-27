package com.example.android_quotes.data.repository.quote

import com.example.android_quotes.data.model.quote.QuoteModel
import com.example.android_quotes.data.network.quote.QuoteService

class QuoteRepository {

    private val api = QuoteService()
    suspend fun getAllQuotes(): List<QuoteModel> {
        val quotes: List<QuoteModel> = api.getQuotes()
        return quotes
    }
}