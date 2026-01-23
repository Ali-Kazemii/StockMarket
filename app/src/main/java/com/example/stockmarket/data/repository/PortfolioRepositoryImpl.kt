package com.example.stockmarket.data.repository

import com.example.stockmarket.domain.model.Holding
import com.example.stockmarket.domain.model.Portfolio
import com.example.stockmarket.domain.model.Stock
import com.example.stockmarket.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class PortfolioRepositoryImpl @Inject constructor() : PortfolioRepository {

    override fun getPortfolio(): Flow<Portfolio> = flow {
        delay(400)
        val holdings = listOf(
            createHolding("AAPL", "Apple Inc.", 178.52, 10.0, 165.0),
            createHolding("MSFT", "Microsoft", 378.91, 5.0, 350.0),
            createHolding("NVDA", "NVIDIA", 495.22, 8.0, 420.0),
            createHolding("GOOGL", "Alphabet", 141.80, 15.0, 135.0),
            createHolding("AMZN", "Amazon", 178.25, 12.0, 160.0)
        )
        val totalValue = holdings.sumOf { it.currentValue }
        val totalCost = holdings.sumOf { it.avgCost * it.shares }
        val dailyChange = totalValue * 0.0125

        emit(Portfolio(
            totalValue = totalValue,
            dailyChange = dailyChange,
            dailyChangePercent = 1.25,
            holdings = holdings,
            valueHistory = generateValueHistory(totalValue)
        ))
    }

    private fun createHolding(symbol: String, name: String, price: Double, shares: Double, avgCost: Double): Holding {
        val currentValue = price * shares
        val costBasis = avgCost * shares
        val profitLoss = currentValue - costBasis
        val profitLossPercent = (profitLoss / costBasis) * 100
        return Holding(
            stock = Stock(symbol, name, price, price * 0.01, 1.0, generatePriceHistory(price)),
            shares = shares,
            avgCost = avgCost,
            currentValue = currentValue,
            profitLoss = profitLoss,
            profitLossPercent = profitLossPercent
        )
    }

    private fun generatePriceHistory(base: Double) = (0..20).map { base * (0.95 + Random.nextDouble() * 0.1) }
    private fun generateValueHistory(base: Double) = (0..30).map { base * (0.92 + Random.nextDouble() * 0.16) }

    override suspend fun refreshPortfolio() { /* Refresh logic */ }
}