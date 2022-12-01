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
            val quoteList: List<Quote> = listOf(
                Quote("Hello World", "Programmers"),
                Quote(
                    "It's not a bug, it's an undocumented feature",
                    "Anonymous"
                )
            )
            coEvery {
                quoteRepository.getAllQuotesFromDatabase()
            } returns quoteList

            //When
            val useCaseResponse: Quote? = getRandomQuoteUseCase()

            //Then
            coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
            coVerify(exactly = 0) { quoteRepository.getAllQuotesFromApi() }
            assert(quoteList.contains(useCaseResponse))
        }

    @Test
    fun `when DDBB is empty then get something from api`(): Unit =
        runBlocking {
            //Given
            val quoteList: List<Quote> = listOf(
                Quote("Hello World", "Programmers"),
                Quote(
                    "It's not a bug, it's an undocumented feature",
                    "Anonymous"
                )
            )
            coEvery {
                quoteRepository.getAllQuotesFromDatabase()
            } returns emptyList()

            coEvery { quoteRepository.getAllQuotesFromApi() } returns quoteList

            //When
            val useCaseResponse: Quote? = getRandomQuoteUseCase()

            //Then
            coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
            coVerify(exactly = 1) { quoteRepository.getAllQuotesFromApi() }
            assert(quoteList.contains(useCaseResponse))
        }

    @Test
    fun `when DDBB is empty and get nothing from api then return null`(): Unit =
        runBlocking {
            //Given
            coEvery {
                quoteRepository.getAllQuotesFromDatabase()
            } returns emptyList()

            coEvery {
                quoteRepository.getAllQuotesFromApi()
            } returns emptyList()

            //When
            val useCaseResponse: Quote? = getRandomQuoteUseCase()

            //Then
            assert(useCaseResponse == null)
        }
}