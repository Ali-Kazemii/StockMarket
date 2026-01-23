package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.model.Stock
import com.example.stockmarket.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopGainersUseCase @Inject constructor(
    private val repository: StockRepository
) {
    operator fun invoke(limit: Int = 10): Flow<List<Stock>> {
        return repository.getTopGainers(limit)
    }
}