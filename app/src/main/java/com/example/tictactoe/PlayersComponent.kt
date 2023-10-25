package com.example.tictactoe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.Pink80
import com.example.tictactoe.ui.theme.Purple40

@Composable
fun Circle() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp),
        onDraw = {
            drawCircle(color = Purple40, style = Stroke(width = 20f))
        }
    )
}

@Composable
fun Cross() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp),
        onDraw = {
            drawLine(
                color = Pink80,
                strokeWidth = 20f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = size.height)
            )
            drawLine(
                color = Pink80,
                strokeWidth = 20f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = size.height),
                end = Offset(x = size.width, y = 0f)
            )
        }
    )
}