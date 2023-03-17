package com.zj.budgetingapp.repo

import com.zj.net.USDCNYRate
import com.zj.net.api.ExchangeRateService

class ExchangeRepoImpl():ExchangeRepo {

    private val service: ExchangeRateService = ExchangeRateService.create()
    override suspend fun getExchangeRate(source:String,currency:String): USDCNYRate {
        return service.exchangeRate(source, currency)
    }
}