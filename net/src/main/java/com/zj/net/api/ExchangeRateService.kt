package com.zj.net.api

import com.zj.net.USDCNYRate
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ExchangeRateService {

    @GET("/currency_data/live")
    @Headers("apikey:Cmo7f1K37C5aqiaJIfG559z64NP1xfU2")
    suspend fun exchangeRate(
        @Query("source") source: String,
        @Query("currencies") currencies: String
    ): USDCNYRate

    companion object {
        private const val BASE_URL = "https://api.apilayer.com/"

        fun create(): ExchangeRateService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExchangeRateService::class.java)
        }
    }
}