package com.example.androidQuotes.data.repository.quote

import com.example.androidQuotes.data.database.quote.QuoteProvider
import com.example.androidQuotes.data.model.quote.QuoteModel
import com.example.androidQuotes.data.network.quote.QuoteService

class QuoteRepository {

    private val api = QuoteService()
    suspend fun getAllQuotes(): List<QuoteModel> {
        val response: List<QuoteModel> = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}