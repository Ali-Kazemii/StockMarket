# 📈 Stock Market App

A modern Android stock market application built with Jetpack Compose, Clean Architecture, and Material 3.

## 🛠 Tech Stack

- **UI:** Jetpack Compose, Material 3
- **Architecture:** Clean Architecture (Domain, Data, Presentation)
- **DI:** Hilt
- **Async:** Kotlin Coroutines, Flow
- **Navigation:** Jetpack Navigation Compose
- **Animations:** Compose Animation APIs

## 📁 Project Structure

```
app/
├── src/main/
│   ├── java/com/example/stockmarket/
│   │   │
│   │   ├── StockMarketApp.kt                 # Hilt Application class
│   │   ├── MainActivity.kt                   # Single Activity entry point
│   │   │
│   │   ├── di/                               # Dependency Injection
│   │   │   └── AppModule.kt                  # Hilt module bindings
│   │   │
│   │   ├── domain/                           # DOMAIN LAYER
│   │   │   ├── model/
│   │   │   │   ├── Stock.kt                  # Stock data model
│   │   │   │   ├── Portfolio.kt              # Portfolio & Holding models
│   │   │   │   └── User.kt                   # User profile model
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── StockRepository.kt        # Stock data interface
│   │   │   │   ├── PortfolioRepository.kt    # Portfolio data interface
│   │   │   │   └── UserRepository.kt         # User data interface
│   │   │   │
│   │   │   └── usecase/
│   │   │       ├── GetTopStocksUseCase.kt    # Fetch top stocks
│   │   │       ├── GetTopGainersUseCase.kt   # Fetch top gainers
│   │   │       ├── GetPortfolioUseCase.kt    # Fetch user portfolio
│   │   │       └── GetUserUseCase.kt         # Fetch user profile
│   │   │
│   │   ├── data/                             # DATA LAYER
│   │   │   └── repository/
│   │   │       ├── StockRepositoryImpl.kt    # Stock repository implementation
│   │   │       ├── PortfolioRepositoryImpl.kt# Portfolio repository implementation
│   │   │       └── UserRepositoryImpl.kt     # User repository implementation
│   │   │
│   │   └── presentation/                     # PRESENTATION LAYER
│   │       ├── theme/
│   │       │   ├── Color.kt                  # App color definitions
│   │       │   └── Theme.kt                  # Material 3 theme setup
│   │       │
│   │       ├── navigation/
│   │       │   ├── Screen.kt                 # Navigation route definitions
│   │       │   ├── BottomNavItem.kt          # Bottom nav item model
│   │       │   └── StockMarketNavigation.kt  # Main navigation host
│   │       │
│   │       ├── components/
│   │       │   ├── LineChart.kt              # Animated portfolio chart
│   │       │   ├── MiniChart.kt              # Small sparkline chart
│   │       │   ├── StockIcon.kt              # Grid stock icon with animation
│   │       │   └── StockListItem.kt          # List item with mini chart
│   │       │
│   │       ├── home/
│   │       │   ├── HomeScreen.kt             # Main dashboard (3 sections)
│   │       │   └── HomeViewModel.kt          # Home screen state management
│   │       │
│   │       ├── portfolio/
│   │       │   ├── PortfolioScreen.kt        # Portfolio holdings screen
│   │       │   └── PortfolioViewModel.kt     # Portfolio state management
│   │       │
│   │       ├── markets/
│   │       │   ├── MarketsScreen.kt          # Top 50 stocks list
│   │       │   └── MarketsViewModel.kt       # Markets state management
│   │       │
│   │       ├── watchlist/
│   │       │   └── WatchlistScreen.kt        # Watchlist placeholder
│   │       │
│   │       └── profile/
│   │           ├── ProfileScreen.kt          # User profile & settings
│   │           └── ProfileViewModel.kt       # Profile state management
│   │
│   ├── res/
│   │   └── values/
│   │       ├── strings.xml
│   │       └── themes.xml
│   │
│   └── AndroidManifest.xml
│
├── build.gradle.kts                          # App-level build config
└── proguard-rules.pro

// Root project files
build.gradle.kts                              # Project-level build config
settings.gradle.kts                           # Project settings
```

## 📱 Features

| Screen | Description |
|--------|-------------|
| **Home** | Portfolio chart, Top 10 stocks grid, Top gainers list |
| **Markets** | Scrollable list of Top 50 US stocks |
| **Portfolio** | Holdings with profit/loss tracking |
| **Watchlist** | Save stocks to watch (placeholder) |
| **Profile** | User info and settings menu |

## 🎨 UI Components

| Component | Description |
|-----------|-------------|
| `LineChart` | Animated line chart with gradient fill |
| `MiniChart` | Compact sparkline for list items |
| `StockIcon` | Tappable stock tile with press animation |
| `StockListItem` | Stock row with chart, price, and change % |


