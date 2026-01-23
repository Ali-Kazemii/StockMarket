package com.example.stockmarket.presentation.markets

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
import com.example.stockmarket.presentation.components.StockListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketsScreen(viewModel: MarketsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            TopAppBar(title = { Text("Markets", fontWeight = FontWeight.Bold) })
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 70.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { Text("Top 50 US Stocks", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }
                itemsIndexed(uiState.stocks) { index, stock ->
                    SlideInFromLeftItem(index = index) {
                        StockListItem(
                            stock = stock,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SlideInFromLeftItem(
    index: Int,
    content: @Composable () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    val offsetX by animateFloatAsState(
        targetValue = if (isVisible) 0f else -300f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "slideInX"
    )
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 300, delayMillis = index * 50),
        label = "fadeIn"
    )

    LaunchedEffect(Unit) {
        isVisible = true
    }

    Box(
        modifier = Modifier
            .graphicsLayer {
                translationX = offsetX
                this.alpha = alpha
            }
    ) {
        content()
    }
}