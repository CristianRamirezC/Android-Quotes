package com.example.androidQuotes.domain.model

import com.example.androidQuotes.data.database.quote.entities.QuoteEntity
import com.example.androidQuotes.data.model.quote.QuoteModel

data class Quote(
    val quote: String,
    val author: String
)

//Mapper from QuoteModel (Data layer Model) to Quote(Domain model)
fun QuoteModel.toDomain() = Quote(quote, author)

//Mapper from QuoteEntity (Database model) to Quote(Domain model)
fun QuoteEntity.toDomain() = Quote(quote, author)
