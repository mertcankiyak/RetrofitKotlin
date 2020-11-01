package com.kodless.retrofitkotlin2.service

import com.kodless.retrofitkotlin2.model.CryptoModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface CryptoAPI {

    //GET, POST, UPDATE, DELETE
    @GET("prices?key=YOURAPIKEY")
    fun getData(): Observable<List<CryptoModel>>

}