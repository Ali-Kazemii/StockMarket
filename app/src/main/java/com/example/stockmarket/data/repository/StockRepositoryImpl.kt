package com.example.stockmarket.data.repository

import com.example.stockmarket.domain.model.Stock
import com.example.stockmarket.domain.repository.StockRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class StockRepositoryImpl @Inject constructor() : StockRepository {

    private val mockStocks = listOf(
        Stock("AAPL", "Apple Inc.", 178.52, 2.34, 1.33, generatePriceHistory(178.52), ""),
        Stock("MSFT", "Microsoft", 378.91, 5.67, 1.52, generatePriceHistory(378.91), ""),
        Stock("GOOGL", "Alphabet", 141.80, -1.23, -0.86, generatePriceHistory(141.80), ""),
        Stock("AMZN", "Amazon", 178.25, 3.45, 1.97, generatePriceHistory(178.25), ""),
        Stock("NVDA", "NVIDIA", 495.22, 12.34, 2.56, generatePriceHistory(495.22), ""),
        Stock("META", "Meta", 505.75, 8.90, 1.79, generatePriceHistory(505.75), ""),
        Stock("TSLA", "Tesla", 248.50, -5.67, -2.23, generatePriceHistory(248.50), ""),
        Stock("BRK.B", "Berkshire", 408.32, 1.23, 0.30, generatePriceHistory(408.32), ""),
        Stock("JPM", "JPMorgan", 195.60, 2.10, 1.08, generatePriceHistory(195.60), ""),
        Stock("V", "Visa", 279.45, 1.89, 0.68, generatePriceHistory(279.45), ""),
        Stock("JNJ", "Johnson & J", 156.78, -0.45, -0.29, generatePriceHistory(156.78), ""),
        Stock("WMT", "Walmart", 165.30, 1.20, 0.73, generatePriceHistory(165.30), ""),
        Stock("PG", "Procter & G", 158.90, 0.67, 0.42, generatePriceHistory(158.90), ""),
        Stock("MA", "Mastercard", 458.25, 3.45, 0.76, generatePriceHistory(458.25), ""),
        Stock("HD", "Home Depot", 345.60, -2.30, -0.66, generatePriceHistory(345.60), ""),
        Stock("CVX", "Chevron", 148.90, 1.56, 1.06, generatePriceHistory(148.90), ""),
        Stock("MRK", "Merck", 108.45, 0.89, 0.83, generatePriceHistory(108.45), ""),
        Stock("ABBV", "AbbVie", 175.30, 2.10, 1.21, generatePriceHistory(175.30), ""),
        Stock("PEP", "PepsiCo", 172.45, -0.78, -0.45, generatePriceHistory(172.45), ""),
        Stock("KO", "Coca-Cola", 60.25, 0.34, 0.57, generatePriceHistory(60.25), "")
    )

    private fun generatePriceHistory(basePrice: Double): List<Double> {
        return (0..20).map { basePrice * (0.95 + Random.nextDouble() * 0.1) }
    }

    override fun getTopStocks(limit: Int): Flow<List<Stock>> = flow {
        delay(500) // Simulate network delay
        emit(mockStocks.take(limit))
    }

    override fun getTopGainers(limit: Int): Flow<List<Stock>> = flow {
        delay(300)
        emit(mockStocks.sortedByDescending { it.changePercent }.take(limit))
    }

    override fun getStockBySymbol(symbol: String): Flow<Stock?> = flow {
        delay(200)
        emit(mockStocks.find { it.symbol == symbol })
    }

    override fun searchStocks(query: String): Flow<List<Stock>> = flow {
        delay(300)
        emit(mockStocks.filter {
            it.symbol.contains(query, ignoreCase = true) ||
                    it.name.contains(query, ignoreCase = true)
        })
    }
}
