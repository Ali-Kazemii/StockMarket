package com.example.stockmarket.presentation.markets


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.model.Stock
import com.example.stockmarket.domain.usecase.GetTopStocksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MarketsUiState(
    val stocks: List<Stock> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class MarketsViewModel @Inject constructor(
    private val getTopStocksUseCase: GetTopStocksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MarketsUiState())
    val uiState: StateFlow<MarketsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTopStocksUseCase(50)
                .catch { /* handle error */ }
                .collect { stocks -> _uiState.update { it.copy(stocks = stocks, isLoading = false) } }
        }
    }
}