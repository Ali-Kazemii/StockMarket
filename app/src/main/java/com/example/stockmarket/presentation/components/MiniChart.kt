package com.example.stockmarket.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.stockmarket.presentation.ui.theme.*


@Composable
fun MiniChart(
    data: List<Double>,
    modifier: Modifier = Modifier,
    isPositive: Boolean = true
) {
    val color = if (isPositive) ChartGreen else ChartRed

    Canvas(modifier = modifier) {
        if (data.size < 2) return@Canvas
        val w = size.width
        val h = size.height
        val maxVal = data.maxOrNull() ?: return@Canvas
        val minVal = data.minOrNull() ?: return@Canvas
        val range = (maxVal - minVal).takeIf { it > 0 } ?: 1.0

        val path = Path().apply {
            data.forEachIndexed { i, value ->
                val x = (i.toFloat() / (data.size - 1)) * w
                val y = h - ((value - minVal) / range * h).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
        }
        drawPath(path = path, color = color, style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round))
    }
}
