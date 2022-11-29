package com.example.androidQuotes.domain.quote

import com.example.androidQuotes.data.repository.quote.QuoteRepository
import com.example.androidQuotes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)

    }

    @Test
    fun `when DDBB is not empty then get the quotes from it`(): Unit =
        runBlocking {
            //Given
            val quoteList = listOf(
                Quote(quote = "Hello World", author = "Programmers")
            )
            coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList

            //When
            getRandomQuoteUseCase()

            //Then
            coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
            coVerify(exactly = 0) { quoteRepository.getAllQuotesFromApi() }
        }

    @Test
    fun `when DDBB is empty then get the quotes from api`(): Unit =
        runBlocking {
            
        }
}