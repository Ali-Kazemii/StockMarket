package com.example.stockmarket.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Screen.Home.route, Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem("Markets", Screen.Markets.route, Icons.Filled.ShowChart, Icons.Outlined.ShowChart),
    BottomNavItem("Portfolio", Screen.Portfolio.route, Icons.Filled.AccountBalance, Icons.Outlined.AccountBalance),
    BottomNavItem("Watchlist", Screen.Watchlist.route, Icons.Filled.Star, Icons.Outlined.StarBorder),
    BottomNavItem("Profile", Screen.Profile.route, Icons.Filled.Person, Icons.Outlined.Person)
)
