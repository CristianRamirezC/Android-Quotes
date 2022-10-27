package com.example.android_quotes.data.model.quote

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("quote") val quote: String = "",
    @SerializedName("author") val author: String = ""
)