package com.example.androidQuotes.ui.viewModel.quote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidQuotes.domain.model.Quote
import com.example.androidQuotes.domain.quote.GetQuotesUseCase
import com.example.androidQuotes.domain.quote.GetRandomQuoteUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    private lateinit var quoteViewModel: QuoteViewModel

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    //Rule for "tricking" the dispatcher in order to test coroutines
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)

        //Tricking the main thread to test coroutines
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        //Resetting the main thread
        Dispatchers.resetMain()
    }

    @Test
    fun `when onCreate is executed and get the quotes then set a random value to quote and author live data`(): Unit =
        runTest {
            //Given
            val quotesList: List<Quote> = listOf(
                Quote("Hello World", "Programmers"),
                Quote(
                    "It's not a bug, it's an undocumented feature",
                    "Anonymous"
                )
            )

            coEvery { getQuotesUseCase() } returns quotesList

            //When
            quoteViewModel.onCreate()

            //Then
            assert(
                quotesList.contains(
                    Quote(
                        quoteViewModel.quote.value!!,
                        quoteViewModel.author.value!!
                    )
                )
            )
            assert(quoteViewModel.isLoading.value == false)
            assert(quoteViewModel.isErrorGettingQuotes.value == false)
            coVerify(exactly = 1) { getQuotesUseCase() }
        }

    @Test
    fun `when onCreate is executed and get no quotes from DDBB nor API then set isErrorGettingQuotes liveData to true`()
            : Unit = runTest {
        //Given
        coEvery { getQuotesUseCase() } returns emptyList()
        //When
        quoteViewModel.onCreate()
        //Then
        coVerify(exactly = 1) { getQuotesUseCase() }
        assert(quoteViewModel.quote.value == null)
        assert(quoteViewModel.author.value == null)
        assert(quoteViewModel.isErrorGettingQuotes.value == true)
        assert(quoteViewModel.isLoading.value == false)
    }

    @Test
    fun `when randomQuote is executed and something is returned then set those values to quote and author live data`()
            : Unit = runTest {
        //Given
        val randomQuote: Quote = Quote(
            quote = "Hello World",
            author = "Programmers"
        )
        coEvery { getRandomQuoteUseCase() } returns randomQuote

        //When
        quoteViewModel.randomQuote()

        //Then
        coVerify(exactly = 1) { getRandomQuoteUseCase() }
        assert(quoteViewModel.quote.value == randomQuote.quote)
        assert(quoteViewModel.author.value == randomQuote.author)
        assert(quoteViewModel.isErrorGettingQuotes.value == false)
        assert(quoteViewModel.isLoading.value == false)
    }

    @Test
    fun `when randomQuote is executed and gets nothing then set isErrorGettingQuotes live date to true`()
            : Unit = runTest {
        //Given
        coEvery { getRandomQuoteUseCase() } returns null
        //When
        quoteViewModel.randomQuote()
        //Then
        coVerify(exactly = 1) { getRandomQuoteUseCase() }
        assert(quoteViewModel.quote.value == null)
        assert(quoteViewModel.author.value == null)
        assert(quoteViewModel.isErrorGettingQuotes.value == true)
        assert(quoteViewModel.isLoading.value == false)
    }
}