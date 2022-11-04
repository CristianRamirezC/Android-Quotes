package com.example.androidQuotes.data.database.quote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidQuotes.data.database.quote.dao.QuoteDao
import com.example.androidQuotes.data.database.quote.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}