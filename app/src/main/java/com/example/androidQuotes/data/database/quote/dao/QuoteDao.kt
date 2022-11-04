package com.example.androidQuotes.data.database.quote.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidQuotes.data.database.quote.entities.QuoteEntity

@Dao
interface QuoteDao {

    @Query(value = "SELECT * FROM quote_table")
    suspend fun getAllQuotes(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quotes: List<QuoteEntity>)
}