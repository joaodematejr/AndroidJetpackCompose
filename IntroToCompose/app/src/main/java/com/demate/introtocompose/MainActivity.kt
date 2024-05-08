package com.demate.introtocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demate.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroToComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val moneyCounter = remember {
        mutableIntStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE0E0E0)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "R$${moneyCounter.intValue}", style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            CreateCircle(
                moneyCounter = moneyCounter.intValue,
                updateMoneyCounter = { newMoneyCounter ->
                    moneyCounter.intValue = newMoneyCounter
                })

            if (moneyCounter.intValue > 25) {
                Text(
                    text = "You are rich!", style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
    }
}

//@Preview
@Composable
fun CreateCircle(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit) {
    //var moneyCounter by remember {
    //    mutableIntStateOf(0)
    //}
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(105.dp)
            .clickable {
                updateMoneyCounter(moneyCounter + 1)
            },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TAP",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(name = "Me", showBackground = true)
@Composable
fun GreetingPreview() {
    IntroToComposeTheme {
        MyApp()
    }
}