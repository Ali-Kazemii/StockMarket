package com.example.stockmarket.presentation.navigation
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Markets : Screen("markets")
    data object Portfolio : Screen("portfolio")
    data object Watchlist : Screen("watchlist")
    data object Profile : Screen("profile")
    data object StockDetail : Screen("stock/{symbol}") {
        fun createRoute(symbol: String) = "stock/$symbol"
    }
}