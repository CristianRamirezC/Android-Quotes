package com.example.android_quotes.ui.viewModel.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_quotes.data.model.quote.QuoteModel
import com.example.android_quotes.data.database.quote.QuoteProvider

class QuoteViewModel : ViewModel() {

    private var _quote = MutableLiveData<String>()
    val quote: LiveData<String> = _quote

    private var _author = MutableLiveData<String>()
    val author: LiveData<String> = _author

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun randomQuote() {
        val currentQuote: QuoteModel = QuoteProvider.random()
        _quote.postValue(currentQuote.quote)
        _author.postValue(currentQuote.author)
    }


}