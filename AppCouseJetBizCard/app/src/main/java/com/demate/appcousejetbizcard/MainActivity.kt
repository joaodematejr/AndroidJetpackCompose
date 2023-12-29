package com.demate.appcousejetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
    ) {
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Surface(
                modifier = Modifier
                    .size(150.dp)
                    .padding(5.dp),
                shape = CircleShape,
                border = BorderStroke(0.5.dp, Color.Black),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
            {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "profile_img",
                    modifier = Modifier.size(135.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppCouseJetBizCardTheme {
        CreateBizCard("2", "2", "2", "2")
    }
}