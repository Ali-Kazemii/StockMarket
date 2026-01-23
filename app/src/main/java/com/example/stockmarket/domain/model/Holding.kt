package com.example.stockmarket.domain.model

data class Holding(
    val stock: Stock,
    val shares: Double,
    val avgCost: Double,
    val currentValue: Double,
    val profitLoss: Double,
    val profitLossPercent: Double
)