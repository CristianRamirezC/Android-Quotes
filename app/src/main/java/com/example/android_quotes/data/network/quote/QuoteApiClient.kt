package com.example.android_quotes.data.network.quote

import com.example.android_quotes.data.model.quote.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("1a4fb01c-e0dd-4ae9-b550-65c0455e9c92")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>
}