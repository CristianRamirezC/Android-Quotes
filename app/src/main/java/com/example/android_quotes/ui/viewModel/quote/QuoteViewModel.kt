package com.example.android_quotes.ui.viewModel.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_quotes.data.model.quote.QuoteModel
import com.example.android_quotes.domain.quote.GetQuotesUseCase
import com.example.android_quotes.domain.quote.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    private var _quote = MutableLiveData<String>()
    val quote: LiveData<String> = _quote

    private var _author = MutableLiveData<String>()
    val author: LiveData<String> = _author

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isErrorGettingQuotes = MutableLiveData<Boolean>()
    val isErrorGettingQuotes: LiveData<Boolean> = _isErrorGettingQuotes

    var getQuotesUseCase = GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val quotes = getQuotesUseCase()
            if (quotes.isEmpty()) {
                _quote.postValue(quotes[0].quote)
                _author.postValue(quotes[0].author)
                _isErrorGettingQuotes.postValue(true)
            } else {
                _isErrorGettingQuotes.postValue(true)
            }
            _isLoading.postValue(false)
        }
    }

    fun randomQuote() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val randomQuote: QuoteModel? = getRandomQuoteUseCase()
            _quote.postValue(randomQuote?.quote)
            _author.postValue(randomQuote?.author)
        }
        _isLoading.postValue(false)

    }

}