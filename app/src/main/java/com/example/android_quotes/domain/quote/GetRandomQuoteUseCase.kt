package com.example.android_quotes.domain.quote

import com.example.android_quotes.data.model.quote.QuoteModel
import com.example.android_quotes.data.repository.quote.QuoteRepository

class GetRandomQuoteUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): QuoteModel {
        val position = (0..10).random()
        val quotes = repository.getAllQuotes()
        return if (quotes.isNotEmpty()) {
            quotes[position]
        } else {
            QuoteModel()
        }
    }
}