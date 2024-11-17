
package com.example.barbellcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.barbellcalculator.ui.theme.BarbellCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarbellCalculatorTheme {
                BarbellCalculatorApp()
            }
        }
    }
}

@Composable
fun BarbellCalculator(modifier: Modifier = Modifier) {
    val availablePlates = mapOf<Int, Color>(
        25 to Color.Red,
        20 to Color.Blue,
        15 to Color.Yellow,
        10 to Color.Green,
        5 to Color.White
    )
    val addedPlates = remember { mutableStateListOf<Int>() }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.height(100.dp)) {
            Image(
                painter = painterResource(R.drawable.bar),
                contentDescription = "bar",
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                for (plate in addedPlates) {
                    val imageRes = when (plate) {
                        25 -> R.drawable.plate_25
                        20 -> R.drawable.plate_20
                        15 -> R.drawable.plate_15
                        10 -> R.drawable.plate_10
                        else -> R.drawable.plate_5
                    }
                    Spacer(modifier = Modifier.width(2.dp))
                    Image(
                        painter = painterResource(imageRes),
                        contentDescription = plate.toString(),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val totalWeight = addedPlates.sum() * 3 + 20
        Text(
            stringResource(R.string.total_barbell_weight, totalWeight),
            modifier = Modifier.padding(16.dp)
        )
        for (plate in availablePlates) {
            Button(onClick = { addedPlates.add(plate.key) }) {
                Row {
                    Text(stringResource(R.string.add_plate, plate.key))
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(plate.value)
                            .padding(4.dp)
                            .width(4.dp)
                            .height(16.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { addedPlates.clear() }, enabled = addedPlates.isNotEmpty()) {
            Text(stringResource(R.string.clear))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BarbellCalculatorApp() {
    BarbellCalculatorTheme {
        BarbellCalculator(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}