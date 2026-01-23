package com.example.stockmarket.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.model.*
import com.example.stockmarket.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val portfolio: Portfolio? = null,
    val topStocks: List<Stock> = emptyList(),
    val topGainers: List<Stock> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPortfolioUseCase: GetPortfolioUseCase,
    private val getTopStocksUseCase: GetTopStocksUseCase,
    private val getTopGainersUseCase: GetTopGainersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init { loadData() }

    fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            combine(
                getPortfolioUseCase(),
                getTopStocksUseCase(10),
                getTopGainersUseCase(10)
            ) { portfolio, topStocks, topGainers ->
                HomeUiState(
                    portfolio = portfolio,
                    topStocks = topStocks,
                    topGainers = topGainers,
                    isLoading = false
                )
            }.catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun refresh() { loadData() }
}
