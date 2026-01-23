package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getTopStocks(limit: Int): Flow<List<Stock>>
    fun getTopGainers(limit: Int): Flow<List<Stock>>
    fun getStockBySymbol(symbol: String): Flow<Stock?>
    fun searchStocks(query: String): Flow<List<Stock>>
}