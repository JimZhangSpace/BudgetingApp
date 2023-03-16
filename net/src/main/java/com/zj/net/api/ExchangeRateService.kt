package com.zj.net.api

import com.zj.net.USDCNYRate
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ExchangeRateService {

    @GET("/currency_data/live")
    @Headers("apikey:Cmo7f1K37C5aqiaJIfG559z64NP1xfU2")
    fun exchangeRate(
        @Query("source") source: String,
        @Query("currencies") currencies: String
    ): USDCNYRate
}