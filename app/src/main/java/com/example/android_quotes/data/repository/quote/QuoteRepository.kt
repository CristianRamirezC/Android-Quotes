package com.example.android_quotes.data.repository.quote

import com.example.android_quotes.data.database.quote.QuoteProvider
import com.example.android_quotes.data.model.quote.QuoteModel
import com.example.android_quotes.data.network.quote.QuoteService

class QuoteRepository {

    private val api = QuoteService()
    suspend fun getAllQuotes(): List<QuoteModel> {
        val response: List<QuoteModel> = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}