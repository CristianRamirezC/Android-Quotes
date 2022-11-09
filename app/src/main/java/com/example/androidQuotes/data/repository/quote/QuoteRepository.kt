package com.example.androidQuotes.data.repository.quote

import com.example.androidQuotes.data.database.quote.QuoteProvider
import com.example.androidQuotes.data.database.quote.dao.QuoteDao
import com.example.androidQuotes.data.database.quote.entities.QuoteEntity
import com.example.androidQuotes.data.model.quote.QuoteModel
import com.example.androidQuotes.data.network.quote.QuoteService
import com.example.androidQuotes.domain.model.Quote
import com.example.androidQuotes.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    suspend fun getAllQuotesFromApi(): List<Quote> {
        val quotesFromApi: List<QuoteModel> = api.getQuotes()
        return quotesFromApi.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val quotesFromDatabase: List<QuoteEntity> = quoteDao.getAllQuotes()
        return quotesFromDatabase.map { it.toDomain() }
    }
}