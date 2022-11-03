package com.example.androidQuotes.domain.quote

import com.example.androidQuotes.data.database.quote.QuoteProvider
import com.example.androidQuotes.data.model.quote.QuoteModel
import com.example.androidQuotes.data.repository.quote.QuoteRepository
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): QuoteModel? {
        val quotes: List<QuoteModel> = QuoteProvider.quotes.ifEmpty {
            repository.getAllQuotes()
        }
        return if (quotes.isNotEmpty()) {
            val randomPosition = quotes.indices.random()
            quotes[randomPosition]
        } else {
            null
        }
    }
}