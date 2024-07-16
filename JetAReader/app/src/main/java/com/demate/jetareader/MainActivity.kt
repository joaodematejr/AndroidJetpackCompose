package com.demate.jetareader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.demate.jetareader.navigation.ReaderNavigation
import com.demate.jetareader.ui.theme.JetAReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetAReaderTheme {
                ReaderApp()
            }
        }
    }
}

@Composable
fun ReaderApp() {
    Surface(color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.fillMaxSize(), content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReaderNavigation()

            }
        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetAReaderTheme {

    }
}