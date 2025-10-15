package com.lotarv.bigsteppa

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun CalendarScreen(onBack: () -> Unit, modifier: Modifier) {
    val days = 31
    val steps = remember { List(days) { Random.nextInt(0, 10000) } }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Октябрь 2025", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.weight(1f)
        ) {
            items(days) { index ->
                val stepCount = steps[index]
                CalendarDayItem(
                    dayNumber = index + 1,
                    steps = stepCount
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.backBtn))
        }
    }
}
