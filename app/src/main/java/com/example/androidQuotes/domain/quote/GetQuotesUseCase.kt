package com.example.androidQuotes.domain.quote

import com.example.androidQuotes.data.model.quote.QuoteModel
import com.example.androidQuotes.data.repository.quote.QuoteRepository
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor() {
    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel> = repository.getAllQuotes()
}