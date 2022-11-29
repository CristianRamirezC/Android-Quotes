package com.example.androidQuotes.domain.quote

import com.example.androidQuotes.data.repository.quote.QuoteRepository
import com.example.androidQuotes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when the api return nothing then get the quotes from DDBB`(): Unit = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        //When
        getQuotesUseCase()


        //Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
        coVerify(exactly = 0) { quoteRepository.clearQuotes() }
        coVerify(exactly = 0) { quoteRepository.insertQuotes(any()) }
    }

    @Test
    fun `when the api returns a list of Quotes then use those values`(): Unit =
        runBlocking {
            //Given
            val quoteList = listOf(Quote(quote = "Hello World", author = "Programmers"))
            coEvery { quoteRepository.getAllQuotesFromApi() } returns quoteList

            //When
            val response = getQuotesUseCase()

            //Then
            coVerify {  }

        }
}