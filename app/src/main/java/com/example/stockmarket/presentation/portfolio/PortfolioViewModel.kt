package com.example.stockmarket.presentation.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.model.Portfolio
import com.example.stockmarket.domain.usecase.GetPortfolioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PortfolioUiState(
    val portfolio: Portfolio? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getPortfolioUseCase: GetPortfolioUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortfolioUiState())
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

    init { loadPortfolio() }

    private fun loadPortfolio() {
        viewModelScope.launch {
            getPortfolioUseCase()
                .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                .collect { portfolio -> _uiState.update { it.copy(portfolio = portfolio, isLoading = false) } }
        }
    }
}
