package com.example.stockmarket.presentation.portfolio

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.stockmarket.domain.model.Holding
import com.example.stockmarket.presentation.components.LineChart
import com.example.stockmarket.presentation.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            TopAppBar(title = { Text("Portfolio", fontWeight = FontWeight.Bold) })
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(Modifier
                .fillMaxSize()
                .padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            uiState.portfolio?.let { portfolio ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 70.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Total Value", style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    "$${String.format("%,.2f", portfolio.totalValue)}",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                LineChart(
                                    data = portfolio.valueHistory,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(120.dp)
                                )
                            }
                        }
                    }

                    item {
                        Text(
                            "Holdings",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    itemsIndexed(portfolio.holdings) { index, holding ->
                        HoldingItem(
                            holding = holding,
                            modifier = Modifier.animateItem()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HoldingItem(
    holding: Holding,
    modifier: Modifier = Modifier
) {
    var hasAnimated by remember { mutableStateOf(false) }
    val animatedAlpha by animateFloatAsState(
        targetValue = if (hasAnimated) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "alpha"
    )
    val animatedOffset by animateFloatAsState(
        targetValue = if (hasAnimated) 0f else 30f,
        animationSpec = tween(durationMillis = 300),
        label = "offset"
    )

    LaunchedEffect(Unit) { hasAnimated = true }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                alpha = animatedAlpha
                translationX = animatedOffset
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(holding.stock.symbol, fontWeight = FontWeight.Bold)
                Text(
                    "${holding.shares} shares @ $${String.format("%.2f", holding.avgCost)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "$${String.format("%,.2f", holding.currentValue)}",
                    fontWeight = FontWeight.Bold
                )
                val isPositive = holding.profitLoss >= 0
                Text(
                    "${if (isPositive) "+" else ""}$${
                        String.format(
                            "%.2f",
                            holding.profitLoss
                        )
                    } (${String.format("%.1f", holding.profitLossPercent)}%)",
                    color = if (isPositive) GreenStock else RedStock,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}