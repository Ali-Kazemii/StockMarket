package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.Portfolio
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun getPortfolio(): Flow<Portfolio>
    suspend fun refreshPortfolio()
}