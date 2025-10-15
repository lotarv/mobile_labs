package com.lotarv.bigsteppa

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun StepTrackerScreen(onOpenCalendar: () -> Unit, modifier: Modifier) {
    var steps by remember { mutableStateOf(0) }
    val animatedSteps by animateIntAsState(steps)
    var goal by remember { mutableStateOf(5000) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineMedium)
            Image(
                painter = painterResource(R.drawable.step),
                contentDescription = "Кроссовок",
                modifier = Modifier.size(256.dp)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$animatedSteps",
                style = MaterialTheme.typography.displayLarge
            )
            Text("${stringResource(R.string.steps)} ${stringResource(R.string.outOf)} $goal", style = MaterialTheme.typography.bodyLarge)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { steps++ }, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.make_step))
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = { steps = 0 }, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.reset))
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                goal = listOf(3000, 5000, 8000, 10000).random()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.goal))
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = onOpenCalendar,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.btnBackground),
                    contentColor = colorResource(R.color.btnText)
                )
            ) {
                Text(stringResource(R.string.calendar))
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTrackerApp() {
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf("main") }
    var expanded by remember { mutableStateOf(false) }
    var langVersion by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { ' ' },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Text("⋮", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Русский") },
                            onClick = {
                                LocaleUtils.setLocale(context, "ru")
                                expanded = false
                                langVersion++
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("English") },
                            onClick = {
                                LocaleUtils.setLocale(context, "en")
                                expanded = false
                                langVersion++
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        key(langVersion) {
            when (currentScreen) {
                "main" -> StepTrackerScreen(
                    onOpenCalendar = { currentScreen = "calendar" },
                    modifier = Modifier.padding(innerPadding)
                )
                "calendar" -> CalendarScreen(
                    onBack = { currentScreen = "main" },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

