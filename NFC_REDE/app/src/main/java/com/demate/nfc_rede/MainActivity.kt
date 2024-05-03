package com.demate.nfc_rede

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.demate.nfc_rede.ui.theme.NFC_REDETheme
import com.demate.nfc_rede.viewmodel.ViewModelRede

class MainActivity : ComponentActivity() {
    private val viewModelRede: ViewModelRede = ViewModelRede()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NFC_REDETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent("João", viewModelRede = viewModelRede)
                }
            }
        }
    }
}

@Composable
fun AppContent(name: String, modifier: Modifier = Modifier, viewModelRede: ViewModelRede) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello $name!", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModelRede.handleInit(context)
        }) {
            Text("Init Context")
        }
        Button(onClick = {
            viewModelRede.readNFC(context)
        }) {
            Text("Serial Card NFC")
        }
        Button(onClick = {
            viewModelRede.handleRead(context)
        }) {
            Text("Read Card NFC")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NFC_REDETheme {
        AppContent("Android", viewModelRede = ViewModelRede())
    }
}