app/
├── src/main/
│   ├── java/com/example/stockmarket/
│   │   │
│   │   ├── StockMarketApp.kt          # Hilt Application
│   │   ├── MainActivity.kt             # Entry point
│   │   │
│   │   ├── di/
│   │   │   └── AppModule.kt            # Hilt DI module
│   │   │
│   │   ├── domain/                     # DOMAIN LAYER
│   │   │   ├── model/
│   │   │   │   ├── Stock.kt
│   │   │   │   ├── Portfolio.kt
│   │   │   │   └── User.kt
│   │   │   ├── repository/
│   │   │   │   ├── StockRepository.kt
│   │   │   │   ├── PortfolioRepository.kt
│   │   │   │   └── UserRepository.kt
│   │   │   └── usecase/
│   │   │       ├── GetTopStocksUseCase.kt
│   │   │       ├── GetTopGainersUseCase.kt
│   │   │       ├── GetPortfolioUseCase.kt
│   │   │       └── GetUserUseCase.kt
│   │   │
│   │   ├── data/                       # DATA LAYER
│   │   │   └── repository/
│   │   │       ├── StockRepositoryImpl.kt
│   │   │       ├── PortfolioRepositoryImpl.kt
│   │   │       └── UserRepositoryImpl.kt
│   │   │
│   │   └── presentation/               # PRESENTATION LAYER
│   │       ├── theme/
│   │       │   ├── Color.kt
│   │       │   └── Theme.kt
│   │       ├── navigation/
│   │       │   ├── Screen.kt
│   │       │   ├── BottomNavItem.kt
│   │       │   └── StockMarketNavigation.kt
│   │       ├── components/
│   │       │   ├── LineChart.kt        # Animated portfolio chart
│   │       │   ├── MiniChart.kt        # Small sparkline chart
│   │       │   ├── StockIcon.kt        # Grid icon with animation
│   │       │   └── StockListItem.kt    # List row with mini chart
│   │       ├── home/
│   │       │   ├── HomeScreen.kt       # Main 3-section screen
│   │       │   └── HomeViewModel.kt
│   │       ├── portfolio/
│   │       │   ├── PortfolioScreen.kt
│   │       │   └── PortfolioViewModel.kt
│   │       ├── markets/
│   │       │   ├── MarketsScreen.kt
│   │       │   └── MarketsViewModel.kt
│   │       ├── watchlist/
│   │       │   └── WatchlistScreen.kt
│   │       └── profile/
│   │           ├── ProfileScreen.kt
│   │           └── ProfileViewModel.kt
│   │
│   ├── res/
│   │   └── values/
│   │       └── themes.xml
│   │
│   └── AndroidManifest.xml
│
├── build.gradle.kts                    # App-level (with dependencies)
└── proguard-rules.pro

// Root project files
build.gradle.kts                        # Project-level
settings.gradle.kts