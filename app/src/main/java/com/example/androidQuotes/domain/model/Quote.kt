package com.example.androidQuotes.domain.model

import com.example.androidQuotes.data.database.quote.entities.QuoteEntity
import com.example.androidQuotes.data.model.quote.QuoteModel

data class Quote(
    val quote: String,
    val author: String
)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)
