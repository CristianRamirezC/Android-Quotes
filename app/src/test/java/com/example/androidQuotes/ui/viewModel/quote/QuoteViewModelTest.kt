package com.example.androidQuotes.ui.viewModel.quote

import com.example.androidQuotes.domain.quote.GetQuotesUseCase
import com.example.androidQuotes.domain.quote.GetRandomQuoteUseCase
import io.mockk.impl.annotations.RelaxedMockK

class QuoteViewModelTest {

    private lateinit var quoteViewModel: QuoteViewModel

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase
}