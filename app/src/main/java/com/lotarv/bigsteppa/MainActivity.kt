package com.lotarv.bigsteppa

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.steptrackercompose.CalendarScreen
import com.lotarv.bigsteppa.ui.theme.BigSteppaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show()
        setContent {
            BigSteppaTheme {
                StepTrackerApp()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun StepTrackerScreen(onOpenCalendar: () -> Unit) {
    var steps by remember { mutableStateOf(0) }
    var goal by remember { mutableStateOf(5000) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Шагомер", style = MaterialTheme.typography.headlineMedium)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$steps",
                style = MaterialTheme.typography.displayLarge
            )
            Text("шагов из $goal", style = MaterialTheme.typography.bodyLarge)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { steps++ }, modifier = Modifier.fillMaxWidth()) {
                Text("Сделать шаг")
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = { steps = 0 }, modifier = Modifier.fillMaxWidth()) {
                Text("Сброс")
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                goal = listOf(3000, 5000, 8000, 10000).random()
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Новое задание")
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = onOpenCalendar, modifier = Modifier.fillMaxWidth()) {
                Text("Календарь")
            }
        }
    }
}



@Composable
fun StepTrackerApp() {
    var currentScreen by remember { mutableStateOf("main") }

    when (currentScreen) {
        "main" -> StepTrackerScreen(
            onOpenCalendar = { currentScreen = "calendar" }
        )
        "calendar" -> CalendarScreen(onBack = { currentScreen = "main" })
    }
}
