package com.example.stockmarket.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.stockmarket.domain.model.Stock
import com.example.stockmarket.presentation.ui.theme.GreenStock
import com.example.stockmarket.presentation.ui.theme.RedStock

@Composable
fun StockListItem(
    stock: Stock,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(stock.symbol, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(stock.name, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

            MiniChart(
                data = stock.priceHistory,
                isPositive = stock.changePercent >= 0,
                modifier = Modifier.width(60.dp).height(30.dp).padding(horizontal = 8.dp)
            )

            Column(horizontalAlignment = Alignment.End) {
                Text("${String.format("%.2f", stock.price)}", fontWeight = FontWeight.Bold)
                val isPositive = stock.changePercent >= 0
                Text(
                    text = "${if (isPositive) "+" else ""}${String.format("%.2f", stock.changePercent)}%",
                    color = if (isPositive) GreenStock else RedStock,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}