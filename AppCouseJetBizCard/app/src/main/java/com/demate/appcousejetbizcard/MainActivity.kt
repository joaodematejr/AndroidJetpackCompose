package com.demate.appcousejetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.demate.appcousejetbizcard.ui.theme.AppCouseJetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCouseJetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard("2", "2", "2", "2")
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(name: String, company: String, phone: String, email: String) {
    Surface(
        modifier = Modifier.fillMaxSize().fillMaxHeight(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppCouseJetBizCardTheme {
        CreateBizCard("2", "2", "2", "2")
    }
}