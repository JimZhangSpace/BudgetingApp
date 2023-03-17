package com.zj.budgetingapp.repo

import com.zj.net.USDCNYRate

interface ExchangeRepo {

    suspend fun getExchangeRate(source:String,currency:String):USDCNYRate

}