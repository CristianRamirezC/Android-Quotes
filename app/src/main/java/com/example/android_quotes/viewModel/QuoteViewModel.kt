package com.example.android_quotes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuoteViewModel : ViewModel() {

    private var _quote = MutableLiveData<String>()
    val quote: LiveData<String> = _quote

    private var _author = MutableLiveData<String>()
    val author: LiveData<String> = _quote

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


}