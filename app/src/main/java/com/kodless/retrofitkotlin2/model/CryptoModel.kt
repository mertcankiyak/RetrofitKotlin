package com.kodless.retrofitkotlin2.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    @SerializedName("currency") //API kaynağında yer alan ifadeler
    val currency: String,

    @SerializedName("price")
    val price: String
)