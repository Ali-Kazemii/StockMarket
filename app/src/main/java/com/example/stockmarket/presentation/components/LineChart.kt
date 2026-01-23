package com.example.stockmarket.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.stockmarket.presentation.ui.theme.*

@Composable
fun LineChart(
    data: List<Double>,
    modifier: Modifier = Modifier,
    lineColor: Color? = null,
    fillGradient: Boolean = true,
    animateChart: Boolean = true
) {
    if (data.isEmpty()) return

    val isPositive = data.last() >= data.first()
    val chartColor = lineColor ?: if (isPositive) ChartGreen else ChartRed

    var animationProgress by remember { mutableFloatStateOf(if (animateChart) 0f else 1f) }

    LaunchedEffect(data) {
        if (animateChart) {
            animationProgress = 0f
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000, easing = EaseOutCubic)
            ) { value, _ -> animationProgress = value }
        }
    }

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val maxVal = data.maxOrNull() ?: return@Canvas
        val minVal = data.minOrNull() ?: return@Canvas
        val range = (maxVal - minVal).takeIf { it > 0 } ?: 1.0

        val points = data.mapIndexed { i, value ->
            val x = (i.toFloat() / (data.size - 1)) * w
            val y = h - ((value - minVal) / range * h).toFloat()
            Offset(x, y)
        }

        val visibleCount = (points.size * animationProgress).toInt().coerceAtLeast(2)
        val visiblePoints = points.take(visibleCount)

        if (fillGradient && visiblePoints.size >= 2) {
            val fillPath = Path().apply {
                moveTo(visiblePoints.first().x, h)
                visiblePoints.forEach { lineTo(it.x, it.y) }
                lineTo(visiblePoints.last().x, h)
                close()
            }
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(chartColor.copy(alpha = 0.3f), Color.Transparent),
                    startY = 0f, endY = h
                )
            )
        }

        if (visiblePoints.size >= 2) {
            val linePath = Path().apply {
                moveTo(visiblePoints.first().x, visiblePoints.first().y)
                for (i in 1 until visiblePoints.size) {
                    val prev = visiblePoints[i - 1]
                    val curr = visiblePoints[i]
                    val cpX = (prev.x + curr.x) / 2
                    cubicTo(cpX, prev.y, cpX, curr.y, curr.x, curr.y)
                }
            }
            drawPath(path = linePath, color = chartColor, style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round))
        }
    }
}