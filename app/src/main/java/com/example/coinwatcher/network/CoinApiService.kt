package com.example.coinwatcher.network

import com.example.coinwatcher.data.dto.CoinDto
import retrofit2.Retrofit
import retrofit2.http.GET

interface CoinApiService {


    @GET("tickers")
    suspend fun getCoins(): List<CoinDto>

}

