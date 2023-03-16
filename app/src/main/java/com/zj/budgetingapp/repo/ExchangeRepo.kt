package com.zj.budgetingapp.repo

import com.zj.net.USDCNYRate

interface ExchangeRepo {

    fun getExchangeRate():USDCNYRate
}