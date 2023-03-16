package com.zj.net

/**
 * live exchange rate
 * USD->CNY
 * */
data class USDCNYRate(
    val quotes: Quotes,
    val source: String,
    val success: Boolean,
    val timestamp: Int
)

data class Quotes(
    val USDCNY: Double
)