package com.lotarv.bigsteppa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CalendarDayItem(
    dayNumber: Int,
    steps: Int,
    modifier: Modifier = Modifier
) {
    val intensity = (steps / 10000f).coerceIn(0f, 1f)
    val bgColor = Color(
        red = (255 - 80 * intensity).toInt(),
        green = (255 - 150 * intensity).toInt(),
        blue = 255
    )

    Box(
        modifier = modifier
            .padding(2.dp)
            .aspectRatio(1f)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(vertical = 8.dp)
        ) {
            Text(
                text = dayNumber.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.DarkGray
            )
            Text(
                text = steps.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )
        }
    }
}
