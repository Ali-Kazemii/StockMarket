package com.example.stockmarket.domain.model

data class Portfolio(
    val totalValue: Double,
    val dailyChange: Double,
    val dailyChangePercent: Double,
    val holdings: List<Holding>,
    val valueHistory: List<Double>
)