package com.example.androidQuotes.domain.quote


import com.example.androidQuotes.data.repository.quote.QuoteRepository
import com.example.androidQuotes.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository,
) {
    suspend operator fun invoke(): Quote? {
        val quotes: List<Quote> = repository.getAllQuotesFromDatabase().ifEmpty {
            repository.getAllQuotesFromApi()
        }
        return if (quotes.isNotEmpty()) {
            val randomPosition = quotes.indices.random()
            quotes[randomPosition]
        } else {
            null
        }
    }
}