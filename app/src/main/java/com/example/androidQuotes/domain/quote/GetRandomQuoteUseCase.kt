package com.example.androidQuotes.domain.quote

import com.example.androidQuotes.data.model.quote.QuoteModel
import com.example.androidQuotes.data.repository.quote.QuoteRepository

class GetRandomQuoteUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): QuoteModel? {
        val quotes = repository.getAllQuotes()
        return if (quotes.isNotEmpty()) {
            val randomPosition = quotes.indices.random()
            quotes[randomPosition]
        } else {
            null
        }
    }
}