package com.demate.appcousejetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    CreateImageProfile()
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight(),
    ) {
        Card(
            modifier = modifier
                .width(300.dp)
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                ImgUser(modifier)
                Divider(
                    thickness = 1.dp,
                    modifier = modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                )
                CreateInfo()
                Button(
                    onClick = {
                        Log.d("TAG", "CreateBizCard: ")
                    },
                    modifier = modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Portfolio",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun Context() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            border = BorderStroke(0.5.dp, Color.Black),
            color = MaterialTheme.colorScheme.background
        ) {
            Portofilo(data = listOf("Port", "Portfolio", "Portfolio", "Portfolio", "Portfolio"))
        }
    }
}

@Composable
fun Portofilo(data: List<String>) {
    Text(text = "Projects go here")
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "João da Silva",
            //styles h4,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Android Development",
            modifier = Modifier.padding(top = 5.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "@joaodematejr",
            modifier = Modifier.padding(top = 5.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

    }
}

@Composable
private fun ImgUser(modifier: Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(10.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Black),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "profile_img",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppCouseJetBizCardTheme {
        CreateBizCard()
    }
}