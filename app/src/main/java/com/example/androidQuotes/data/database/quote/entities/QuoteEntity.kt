package com.example.androidQuotes.data.database.quote.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidQuotes.domain.model.Quote

@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "quote")
    val quote: String,

    @ColumnInfo(name = "author")
    val author: String
)

//Mapper from Quote(Domain model) to Entity (Database model)
fun Quote.toDatabase() = QuoteEntity(quote = quote, author = author)