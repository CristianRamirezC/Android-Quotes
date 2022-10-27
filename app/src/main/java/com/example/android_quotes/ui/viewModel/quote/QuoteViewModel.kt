package com.example.android_quotes.ui.viewModel.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_quotes.data.model.quote.QuoteModel
import com.example.android_quotes.data.database.quote.QuoteProvider
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

    var getQuoteUseCase = GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getQuoteUseCase()
            if (result.isNotEmpty()) {
                _quote.postValue(result[0].quote)
                _author.postValue(result[0].author)
            }
            _isLoading.postValue(false)
        }
    }

    fun randomQuote() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val result = getRandomQuoteUseCase()
            _quote.postValue(result.quote)
            _author.postValue(result.author)
        }
        _isLoading.postValue(false)

    }

}