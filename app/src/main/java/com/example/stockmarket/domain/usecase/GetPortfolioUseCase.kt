package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.model.Portfolio
import com.example.stockmarket.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPortfolioUseCase @Inject constructor(
    private val repository: PortfolioRepository
) {
    operator fun invoke(): Flow<Portfolio> {
        return repository.getPortfolio()
    }
}