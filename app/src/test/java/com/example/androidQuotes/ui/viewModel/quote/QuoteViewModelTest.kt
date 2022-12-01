package com.example.androidQuotes.ui.viewModel.quote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidQuotes.domain.model.Quote
import com.example.androidQuotes.domain.quote.GetQuotesUseCase
import com.example.androidQuotes.domain.quote.GetRandomQuoteUseCase
import io.mockk.MockKAnnotations
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
    fun `when viewModel is created for the first time, get the quotes and set a random value`(): Unit =
        runTest {
            //Given
            val quotes: List<Quote> = listOf(
                Quote("Hello World", "Programmers"),
                Quote(
                    "It's not a bug, it's an undocumented feature",
                    "Anonymous"
                )
            )
            //When
            //Then

        }
}