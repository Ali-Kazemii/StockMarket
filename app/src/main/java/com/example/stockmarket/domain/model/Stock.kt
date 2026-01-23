package com.example.stockmarket.domain.model

data class Stock(
    val symbol: String,
    val name: String,
    val price: Double,
    val change: Double,
    val changePercent: Double,
    val priceHistory: List<Double> = emptyList(),
    val logoUrl: String = ""
)