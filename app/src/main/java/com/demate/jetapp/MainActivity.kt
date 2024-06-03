package com.demate.jetapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demate.jetapp.ui.theme.JetAppTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                TopHeader()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

//@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 0.0) {
    val locale = Locale("pt", "BR")
    val currencyFormat = NumberFormat.getCurrencyInstance(locale)
    val formattedTotal = currencyFormat.format(totalPerPerson)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0x0FC40EA3)
        //.clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total per person",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                //format pt-br
                text = formattedTotal,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview
@Composable
fun MainContext() {
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column() {
            Text(text = "Hello again....")
            Text(text = "Hello again....")
            Text(text = "Hello again....")
            Text(text = "Hello again....")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetAppTheme {
        MyApp {
            Text(text = "ola Mundo")
        }
    }
}