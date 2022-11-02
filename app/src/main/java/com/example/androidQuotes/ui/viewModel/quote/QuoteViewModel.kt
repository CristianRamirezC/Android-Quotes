package com.example.androidQuotes.ui.viewModel.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidQuotes.data.model.quote.QuoteModel
import com.example.androidQuotes.domain.quote.GetQuotesUseCase
import com.example.androidQuotes.domain.quote.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel
@Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    private var _quote = MutableLiveData<String>()
    val quote: LiveData<String> = _quote

    private var _author = MutableLiveData<String>()
    val author: LiveData<String> = _author

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isErrorGettingQuotes = MutableLiveData<Boolean>()
    val isErrorGettingQuotes: LiveData<Boolean> = _isErrorGettingQuotes

    fun onCreate() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val quotes = getQuotesUseCase()
            if (quotes.isNotEmpty()) {
                val firstQuoteToShow: QuoteModel = quotes[quotes.indices.random()]
                _quote.postValue(firstQuoteToShow.quote)
                _author.postValue(firstQuoteToShow.author)
            } else {
                _isErrorGettingQuotes.postValue(true)
            }
            _isLoading.postValue(false)
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            _isErrorGettingQuotes.postValue(false)
            val randomQuote: QuoteModel? = getRandomQuoteUseCase()
            if (randomQuote != null) {
                _quote.postValue(randomQuote.quote)
                _author.postValue(randomQuote.author)
            } else {
                _isErrorGettingQuotes.postValue(true)
            }
            _isLoading.postValue(false)
        }
    }
}