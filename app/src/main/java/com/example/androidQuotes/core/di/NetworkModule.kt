package com.example.androidQuotes.core.di

import com.example.androidQuotes.data.network.quote.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mocki.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): QuoteApiClient {
        return retrofit.create(QuoteApiClient::class.java)
    }
}