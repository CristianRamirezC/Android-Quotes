package com.example.android_quotes.domain.quote

import com.example.android_quotes.data.model.quote.QuoteModel
import com.example.android_quotes.data.repository.quote.QuoteRepository

class GetQuotesUseCase {
    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel> = repository.getAllQuotes()
}